package com.prog4.progtd.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class MultipartFileToByteArrayConverter implements Converter<MultipartFile, byte[]> {
    @Override
    public byte[] convert(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            return null;
        }
    }
}

