package vn.pvhg.socialbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.pvhg.socialbackend.model.authentication.User;
import vn.pvhg.socialbackend.model.enums.Gender;

import java.text.Normalizer;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    private UUID id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(nullable = false)
    private String displayName;

    @Column(nullable = false, unique = true)
    private String handleName;

    @Column(columnDefinition = "TEXT")
    private String bio;

    private String profileImage;

    @Column(name = "location")
    private String location;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    @PrePersist
    @PreUpdate
    private void prePersist() {
        String normalized = Normalizer.normalize(displayName, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")      // remove accents
                .replaceAll("[^a-zA-Z0-9]", "")       // remove non-alphanumeric
                .toLowerCase();

        String randomSuffix = UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        this.handleName = normalized + randomSuffix;
    }
}
