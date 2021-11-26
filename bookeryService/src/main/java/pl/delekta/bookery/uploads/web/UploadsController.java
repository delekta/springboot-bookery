package pl.delekta.bookery.uploads.web;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceChainRegistration;
import pl.delekta.bookery.uploads.application.ports.UploadUseCase;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/upload")
@AllArgsConstructor
public class UploadsController {
    private final UploadUseCase upload;

    @GetMapping("/{id}")
    public ResponseEntity<UploadResponse> getUpload(@PathVariable String id) {
        return upload.getById(id)
                .map(uploadFile -> {
                    UploadResponse response = new UploadResponse(
                            uploadFile.getId(),
                            uploadFile.getContentType(),
                            uploadFile.getFilename(),
                            uploadFile.getCreatedAt()
                    );
                    return ResponseEntity.ok(response);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> getUploadFile(@PathVariable String id) {
        return upload.getById(id)
                .map(uploadFile -> {
                    String contentDisposition = "attachment; filename=\"" + uploadFile.getFilename() + "\"";
                    Resource resource = new ByteArrayResource(uploadFile.getFile());
                    return ResponseEntity
                            .ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                            .contentType(MediaType.parseMediaType(uploadFile.getContentType()))
                            .body(resource);
                }).orElse(ResponseEntity.notFound().build());
    }

    @Value
    @AllArgsConstructor
    static class UploadResponse {
        String id;
        String contentType;
        String filename;
        LocalDateTime createdAt;
    }
}
