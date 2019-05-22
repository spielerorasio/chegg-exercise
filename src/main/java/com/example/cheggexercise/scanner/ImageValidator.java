package com.example.cheggexercise.scanner;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
//taken from https://www.mkyong.com/regular-expressions/how-to-validate-image-file-extension-with-regular-expression/
@Component
public class ImageValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String IMAGE_PATTERN =   "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public ImageValidator(){
        pattern = Pattern.compile(IMAGE_PATTERN);
    }

    /**
     * Validate image with regular expression
     * @param image image for validation
     * @return true valid image, false invalid image
     */
    public boolean isImage(final String image){

        matcher = pattern.matcher(image);
        return matcher.matches();

    }
}