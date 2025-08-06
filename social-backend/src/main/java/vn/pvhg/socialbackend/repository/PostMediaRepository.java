package vn.pvhg.socialbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.pvhg.socialbackend.model.post.PostMedia;

import java.util.UUID;

public interface PostMediaRepository extends JpaRepository<PostMedia, UUID> {
}