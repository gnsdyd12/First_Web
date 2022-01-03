package hello.hellospring.post;

import hello.hellospring.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post member);
    List<PostDto.PostListDto> findByAll();
    Optional<PostDto.PostDetailDto> findById(Long id);
    void view_Count(Long id);
}
