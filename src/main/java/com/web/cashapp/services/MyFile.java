package com.web.cashapp.services;

import java.io.IOException;

public interface MyFile {
    boolean save(String json);

    String read();

    void clear() throws IOException;
}
