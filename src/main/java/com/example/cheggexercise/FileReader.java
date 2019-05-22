package com.example.cheggexercise;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @CreatedBy Orasio Spieler
 * May 20 2019
 */
public interface FileReader {

    /**
     * Use NIO to avoid load to memory - we copy the file to tmp and delete it later
     * @param linksURL the url of the file that contain the data source
     * @return Path to temp file
     * @throws IOException if failing to read
     */
    default Path copyRemoteFile(String linksURL) throws IOException {
        String fileName = linksURL.substring(linksURL.lastIndexOf('/')+1);
        Path tempFile = Files.createTempFile(fileName, null);

        try(ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(linksURL).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile.toFile()) ) {

            fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            return tempFile;

        }
    }
}
