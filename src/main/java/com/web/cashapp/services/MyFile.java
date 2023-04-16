package com.web.cashapp.services;

import java.io.IOException;
import java.nio.file.Path;

public interface MyFile {
    boolean save(String json);

    String read();

    void clear() throws IOException;

    Path createTemp(String sfx) throws IOException;
}
