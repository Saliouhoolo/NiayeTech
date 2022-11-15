package sn.niayetch.niayetech.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class FileUploadUtil {
    public static String saveFile(String fileName, MultipartFile multipartFile,String directory) throws IOException {
        Path uploadPath = Paths.get(directory);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        return directory+"/"+fileName;
    }
}
