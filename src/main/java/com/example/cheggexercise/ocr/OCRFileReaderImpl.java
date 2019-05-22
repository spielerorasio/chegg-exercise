package com.example.cheggexercise.ocr;

import com.example.cheggexercise.dto.OCRResponses;
import com.example.cheggexercise.event.CreateQuestionEvent;
import com.example.cheggexercise.FileReader;
import com.example.cheggexercise.domain.SourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
@Component
public class OCRFileReaderImpl implements OCRFileReader, FileReader {
    private static final Logger logger = LoggerFactory.getLogger(OCRFileReaderImpl.class);
    private static final String REST_BODY_STR = "{\"requests\": [{\"image\": {\"content\": \"%s\"},\"features\": [{\"type\": \"TEXT_DETECTION\"}]}]}";
    private static final String REST_BODY_URL = "https://vision.googleapis.com/v1/images:annotate?key=%s";


    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Override
    public void readQuestionFromOCR(String url) throws IOException {

        Path path = copyRemoteFile(url);
        readQuestionFromOCR(path);
        path.toFile().delete();
    }

    @Override
    public void readQuestionFromOCR(Path path) throws IOException {
        String bodyStr = String.format(REST_BODY_STR, encodeFileToBase64Binary(path));
        String ocrURL = String.format(REST_BODY_URL, System.getenv(GOOGLE_APPLICATION_API_KEY));
        OCRResponses ocrResponse = sendToOCRUsingRest(bodyStr, ocrURL);
        for (com.example.cheggexercise.dto.OCRResponse OCRResponse : ocrResponse.getResponses()) {
            String text = OCRResponse.getFullTextAnnotation().getText();
            int indexOf = text.indexOf('\n');
            applicationEventPublisher.publishEvent(
                    new CreateQuestionEvent(SourceType.image, text.substring(0,indexOf), text.substring(indexOf+1)));
        }
    }


    private OCRResponses sendToOCRUsingRest(String bodyStr, String getServerUrl)  {

        try{
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity =  new HttpEntity(bodyStr,headers);

            final ResponseEntity<OCRResponses> responseEntity = restTemplate.postForEntity(getServerUrl, entity, OCRResponses.class);

            final HttpStatus statusCode = responseEntity.getStatusCode();
            if(statusCode.is4xxClientError() || statusCode.is5xxServerError()){
                logger.error("Got an exception from vision.googleapis.com  http status {} , reason {} , url {}", statusCode.value(), statusCode.getReasonPhrase(), getServerUrl);
                //throw new FileStorageException(statusCode.getReasonPhrase());
                System.out.println(statusCode.getReasonPhrase());
            }
            return responseEntity.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException exp){
            logger.error("Got an exception from vision.googleapis.com http status {} , reason {} , url {} ", exp.getStatusCode().value(), exp.getMessage(), getServerUrl);
            //throw new FileStorageException(exp.getMessage(), exp);
        } catch (RestClientException exp){
            logger.error("Got an exception from vision.googleapis.com, reason {} , url {} ",  exp.getMessage(), getServerUrl);
            //throw new FileStorageException(exp.getMessage(), exp);
        }
        return null;
    }

    private static String encodeFileToBase64Binary(Path path) throws IOException {
        byte[] encoded = java.util.Base64.getEncoder().encode(Files.readAllBytes(path));
        return new String(encoded,StandardCharsets.US_ASCII);

    }


}
