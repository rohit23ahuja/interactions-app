package dev.rohitahuja.ingestion;

import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {

    private final VectorStore vectorStore;
    private final List<String> filesInContext = new ArrayList<>();

    public FileUploadService(VectorStore vectorStore) throws IOException {
        this.vectorStore = vectorStore;
    }

    public void addFileToContext(MultipartFile file) throws IOException {
        var resource = new InputStreamResource(file.getInputStream());
        vectorStore.write(new TokenTextSplitter().apply(new TikaDocumentReader(resource).read()));

        filesInContext.add(file.getOriginalFilename());
    }
}
