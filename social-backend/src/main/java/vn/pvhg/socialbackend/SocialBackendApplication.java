package vn.pvhg.socialbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import vn.pvhg.socialbackend.properties.JwtKeyProperties;
import vn.pvhg.socialbackend.properties.UploadDirProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtKeyProperties.class, UploadDirProperties.class})
public class SocialBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialBackendApplication.class, args);
    }

}
