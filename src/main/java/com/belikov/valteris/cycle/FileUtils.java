package com.belikov.valteris.cycle;

import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            if (Files.exists(filePath)) {
                return;
            }
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
    public static void removeFile(String cacheDir, String fileName) throws IOException {
        Path removePath = Paths.get(cacheDir + "/" + fileName);
       try {
           Files.deleteIfExists(removePath);
       } catch (IOException exception) {
           throw new IOException("Could not remove image file: " + fileName, exception);
       }
    }
}
