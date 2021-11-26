package pl.delekta.bookery.uploads.application.ports;

import lombok.Value;
import pl.delekta.bookery.uploads.domain.Upload;

import java.util.Optional;

public interface UploadUseCase {
    Upload save(SaveUploadCommand command);

    Optional<Upload> getById(String id);

    void removeById(String id);

    @Value
    class SaveUploadCommand {
        String filename;
        byte[] file;
        String contentType;
    }
}
