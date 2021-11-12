package pl.delekta.bookery.uploads.application.ports;

import lombok.Value;
import pl.delekta.bookery.uploads.domain.Upload;

public interface UploadUseCase {
    Upload save(SaveUploadCommand command);

    @Value
    class SaveUploadCommand {
        String filename;
        byte[] file;
        String contentType;
    }
}
