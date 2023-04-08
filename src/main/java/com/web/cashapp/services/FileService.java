package com.web.cashapp.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FileService implements File {
    @Value("name")
    private String path;

    @Override
    public boolean save(String json) {
        try {
            Files.writeString(Path.of(path), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String read() {
        try {
            clear();
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private boolean clear() {
        try {
            Files.deleteIfExists(Path.of(path));
            Files.createFile(Path.of(path));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
