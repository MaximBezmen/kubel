package com.kubel.utils;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileDownlandUtil {

    private FileDownlandUtil() {

    }

    public static byte[] addFileToDto(String path) throws IOException {
        InputStream in = new FileInputStream(path);
        return IOUtils.toByteArray(in);
    }

//    public static String addFileToDto(String path) throws IOException {
//        byte[] fileContent = FileUtils.readFileToByteArray(new File(path));
//        String encodedString = Base64.getEncoder().encodeToString(fileContent);
//        return encodedString;
//    }
}
