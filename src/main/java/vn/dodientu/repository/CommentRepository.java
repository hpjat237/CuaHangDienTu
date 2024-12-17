package vn.dodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.dodientu.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
