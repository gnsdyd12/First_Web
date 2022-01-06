package hello.hellospring.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Long> {

    @Override
    Page<Post> findAll(Pageable pageable);

    List<PostDto.PostListDto> findAllByIdIsNotNull();
}
