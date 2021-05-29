package com.kubel.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileUploadUtil {
    private FileUploadUtil() {

    }

    public static String saveFile(String uploadDir, String file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        String fileName = RandomStringUtils.randomAlphabetic(10);
        if (file.contains("data:image/jpeg;")) {
            fileName = fileName + ".jpg";
        } else {
            fileName = fileName + ".png";
        }
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String s = uploadPath.toAbsolutePath() + "\\" + fileName;

        String partSeparator = ",";
        if (file.contains(partSeparator)) {
            String encodedImg = file.split(partSeparator)[1];
            byte[] decodedImg = Base64.getDecoder().decode(encodedImg.getBytes(StandardCharsets.UTF_8));
            FileUtils.writeByteArrayToFile(new File(s), decodedImg);

        }
        return s;
    }
}
