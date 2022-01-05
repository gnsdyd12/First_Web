package hello.hellospring.post;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PostService {


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PostRepo postRepo;

    public PostService( PostRepo postRepo) {

        this.postRepo = postRepo;
    }

    public void save(Post post) {
        String encodePassword = passwordEncoder.encode(post.getPw());
        post.setPw(encodePassword);
        postRepo.save(post);
    }

    public List<PostDto.PostListDto> findByAll() {
        return postRepo.findAllByIdIsNotNull();
    }


    public Optional<PostDto.PostDetailDto> findById(Long id) {
        Optional<Post> post =  postRepo.findById(id);
        if(post.isEmpty()){
            throw new RuntimeException("아이디가 없습니다");
        }else{
            return Optional.of(new PostDto.PostDetailDto(post.get()));
        }
    }

    public Optional<PostDto.PostModifyDto> findByIdForModify(Long id) {
        Optional<Post> post =  postRepo.findById(id);
        if(post.isEmpty()){
            throw new RuntimeException("아이디가 없습니다");
        }else{
            return Optional.of(new PostDto.PostModifyDto(post.get()));
        }
    }
    public boolean modify(PostDto.PostModifyDto postModifyDto) {
        Optional<Post> post = postRepo.findById(postModifyDto.getId());
        AtomicBoolean check = new AtomicBoolean(false);
        post.ifPresent(m -> {
            String pw = post.get().getPw();
            String pw2 = postModifyDto.getPassword();

            if (passwordEncoder.matches(pw2, pw)) {
                m.modify(postModifyDto);
                postRepo.save(m);
                check.set(true);
            } else {
                check.set(false);
            }
        });
        return check.get();
    }


    public void view_Count(Long id) {
        Optional<Post> post= postRepo.findById(id);
        post.ifPresent(m->{
           m.setViews(m.getViews()+1L);
           postRepo.save(m);
        });
    }

    public Optional<PostDto.PostDeleteDto> findByIdForDelete(Long id){
        Optional<Post> post = postRepo.findById(id);
        if(post.isEmpty()){
            throw new RuntimeException("아이디가 없습니다");
        }else{
            return Optional.of(new PostDto.PostDeleteDto(post.get()));
        }
    }

    public boolean delete(PostDto.PostDeleteDto postDeleteDto){


        Optional<Post> post = postRepo.findById(postDeleteDto.getId());

        AtomicBoolean check = new AtomicBoolean(false);

        post.ifPresent(m->{
            String pw = post.get().getPw();
            String pw2 = postDeleteDto.getPassword();
            if (passwordEncoder.matches(pw2, pw)) {
                postRepo.delete(post.get());
                check.set(true);
            } else {
                check.set(false);
            }
        });
        return check.get();
    }
}
