package com.example.hwokhttp.requestBody;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;

public class FileProgressRequestBody extends RequestBody {
    private File file;
    private ProgressListener progressListener;
    public interface ProgressListener {
        void transferred( long size );
    }
    public FileProgressRequestBody(File file,ProgressListener progressListener) {
        this.file = file;
        this.progressListener=progressListener;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse("application/octet-stream");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Okio.source(file);
    }

    @Override
    public long contentLength() throws IOException {
        return file.length();
    }
}
