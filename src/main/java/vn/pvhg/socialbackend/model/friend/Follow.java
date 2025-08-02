package vn.pvhg.socialbackend.model.friend;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import vn.pvhg.socialbackend.model.authentication.User;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "follows", uniqueConstraints = @UniqueConstraint(columnNames = {"following_id", "followed_id"}))
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The authenticated user (who initiates the follow action).
     * Example: If Alice follows Bob, `following = Alice`.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id")
    private User following;

    /**
     * The user being followed by the authenticated user.
     * Example: If Alice follows Bob, `followed = Bob`.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id")
    private User followed;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant followedAt;
}
