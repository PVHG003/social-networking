package vn.pvhg.socialbackend.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;

@ConfigurationProperties(prefix = "file")
public record UploadDirProperties(
        Path imageUpload,
        Path videoUpload
) {
}
