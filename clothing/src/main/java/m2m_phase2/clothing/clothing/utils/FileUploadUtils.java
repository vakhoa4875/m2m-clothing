package m2m_phase2.clothing.clothing.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtils {

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            Files.copy(multipartFile.getInputStream(), uploadPath.resolve(fileName));
        } catch (IOException ex) {
            throw new IOException("Could not save file: " + fileName, ex);
        }
    }
}
