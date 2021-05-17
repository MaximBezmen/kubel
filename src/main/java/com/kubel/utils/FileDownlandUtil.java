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

//    public  byte[] getImageWithMediaType(String imageName) throws IOException {
//        Path destination =   Paths.get(storageDirectoryPath+"\\"+imageName);// retrieve the image by its name
//
//        return IOUtils.toByteArray(destination.toUri());
//    }
}
