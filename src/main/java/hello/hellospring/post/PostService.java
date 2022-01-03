package hello.hellospring.post;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final JdbcPostRepo jdbcPostRepo;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public PostService(JdbcPostRepo jdbcPostRepo) {
        this.jdbcPostRepo = jdbcPostRepo;
    }

    public void save(Post post) {
        String encodePassword = passwordEncoder.encode(post.getPw());
        post.setPw(encodePassword);
        jdbcPostRepo.save(post);
    }

    public List<PostDto.PostListDto> findByAll(){
        return jdbcPostRepo.findByAll();
    }

    public Optional<PostDto.PostDetailDto> findById(Long id){
        return jdbcPostRepo.findById(id);
    }

    public void view_Count(Long id) { jdbcPostRepo.view_Count(id);}

}
