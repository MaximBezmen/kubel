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
}
