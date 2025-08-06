package vn.pvhg.socialbackend.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import vn.pvhg.socialbackend.exception.FileStorageException;
import vn.pvhg.socialbackend.exception.FileValidationException;
import vn.pvhg.socialbackend.model.enums.MediaType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileUploadUtils {

    private static final String BASE_STORAGE_PATH = "uploads";
    private static final long MAX_IMAGE_SIZE = 5L * 1024 * 1024; // 5MB
    private static final long MAX_VIDEO_SIZE = 50L * 1024 * 1024; // 50MB

    private static final List<String> ALLOWED_IMAGE_TYPES = List.of(
            "image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp"
    );

    private static final List<String> ALLOWED_VIDEO_TYPES = List.of(
            "video/mp4", "video/mpeg", "video/webm", "video/quicktime"
    );

    private final ResourceLoader resourceLoader;

    public void deleteFile(String filePath) throws FileNotFoundException {
        try {
            Path absolutePath = Paths.get(BASE_STORAGE_PATH).resolve(filePath).normalize();
            Files.deleteIfExists(absolutePath);
        } catch (IOException e) {
            throw new FileNotFoundException("Could not read file: " + filePath);
        }
    }

    public Resource loadFileAsResource(String filePath) throws FileNotFoundException {
        try {
            Path absolutePath = Paths.get(BASE_STORAGE_PATH).resolve(filePath).normalize();
            Resource resource = resourceLoader.getResource("file:" + absolutePath);

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found: " + filePath);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Could not read file: " + filePath);
        }
    }

    public List<String> storeFiles(Path relativePath, List<MultipartFile> files) {
        List<String> storedFilePaths = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String storedFile = storeFile(relativePath, file);
                storedFilePaths.add(storedFile);
            } catch (IOException e) {
                throw new FileStorageException("Failed to store file " + file.getOriginalFilename(), e);
            }
        }
        return storedFilePaths;
    }

    private String storeFile(Path relativePath, MultipartFile file) throws IOException {
        validateFile(file);
        Path absolutePath = createDirectories(relativePath);
        String filename = generateFilename(file.getOriginalFilename());
        Path targetLocation = absolutePath.resolve(filename);

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        return relativePath.resolve(filename).toString();
    }

    private String generateFilename(String originalFilename) {
        String sanitized = originalFilename.replaceAll("[^a-zA-Z0-9.-]", "_");
        return UUID.randomUUID() + "_" + sanitized;
    }

    private Path createDirectories(Path relativePath) throws IOException {
        Path absolutePath = Paths.get(BASE_STORAGE_PATH).resolve(relativePath).normalize();
        if (!Files.exists(absolutePath)) {
            Files.createDirectories(absolutePath);
        }
        return absolutePath;
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileValidationException("File is empty");
        }
        String contentType = file.getContentType();
        if (ALLOWED_IMAGE_TYPES.contains(contentType)) {
            if (file.getSize() > MAX_IMAGE_SIZE) {
                throw new FileValidationException("File is too large");
            }
        } else if (ALLOWED_VIDEO_TYPES.contains(contentType)) {
            if (file.getSize() > MAX_VIDEO_SIZE) {
                throw new FileValidationException("File is too large");
            }
        } else {
            throw new FileValidationException("File type not supported, supported content type: " + ALLOWED_IMAGE_TYPES + ALLOWED_VIDEO_TYPES);
        }
    }

    public String getOriginalFileName(String item) {
        Path relativePath = Paths.get(BASE_STORAGE_PATH).resolve(item).normalize();
        return relativePath.getFileName().toString();
    }

    public long getFileSize(String item) {
        Path relativePath = Paths.get(BASE_STORAGE_PATH).resolve(item).normalize();
        return relativePath.toFile().length();
    }

    public MediaType getMediaType(String item) {
        Path relativePath = Paths.get(BASE_STORAGE_PATH).resolve(item).normalize();
        try {
            String contentType = Files.probeContentType(relativePath);
            if (contentType == null) {
                contentType = URLConnection.guessContentTypeFromName(relativePath.getFileName().toString());
            }
            return determineMediaType(contentType);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + relativePath, e);
        }
    }

    private MediaType determineMediaType(String contentType) {
        if (contentType == null) {
            return MediaType.OTHER;
        }
        if (contentType.startsWith("image/")) {
            return MediaType.IMAGE;
        } else if (contentType.startsWith("video/")) {
            return MediaType.VIDEO;
        } else if (contentType.startsWith("audio/")) {
            return MediaType.AUDIO;
        } else {
            return MediaType.OTHER;
        }
    }

    public Dimension getDimension(String item) {
        Path relativePath = Paths.get(BASE_STORAGE_PATH).resolve(item).normalize();
        String contentType = getMimeType(item);
        if (ALLOWED_IMAGE_TYPES.contains(contentType)) {
            try {
                BufferedImage bufferedImage = ImageIO.read(relativePath.toFile());
                if (bufferedImage == null) {
                    throw new RuntimeException("Unsupported or corrupt image: " + relativePath);
                }
                return new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight());
            } catch (IOException e) {
                throw new RuntimeException("Could not read file: " + relativePath, e);
            }
        }
        return null;
    }

    public Integer getDuration(String item) {
        if (!determineMediaType(item).equals(MediaType.VIDEO)) {
            return null;
        }
        return 1_000_000;
    }

    public String getMimeType(String item) {
        Path relativePath = Paths.get(BASE_STORAGE_PATH).resolve(item).normalize();
        try {
            String contentType = Files.probeContentType(relativePath);
            if (contentType == null) {
                contentType = URLConnection.guessContentTypeFromName(relativePath.getFileName().toString());
            }
            return contentType;
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + relativePath, e);
        }

    }

    public void deleteDirectory(String storagePath) throws FileNotFoundException {
        try {
            Path absolutePath = Paths.get(BASE_STORAGE_PATH).resolve(storagePath).getParent().normalize();
            log.info("Delete directory {}", absolutePath);
            Files.deleteIfExists(absolutePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not read file: " + storagePath);
        }
    }
}
