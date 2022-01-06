package hello.hellospring.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class PostService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PostRepo postRepo;


    public PostService(PostRepo postRepo) {

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
        Optional<Post> post = postRepo.findById(id);
        if (post.isEmpty()) {
            throw new RuntimeException("아이디가 없습니다");
        } else {
            return Optional.of(new PostDto.PostDetailDto(post.get()));
        }
    }

    public Optional<PostDto.PostModifyDto> findByIdForModify(Long id) {
        Optional<Post> post = postRepo.findById(id);
        if (post.isEmpty()) {
            throw new RuntimeException("아이디가 없습니다");
        } else {
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
        Optional<Post> post = postRepo.findById(id);
        post.ifPresent(m -> {
            m.setViews(m.getViews() + 1L);
            postRepo.save(m);
        });
    }

    public Optional<PostDto.PostDeleteDto> findByIdForDelete(Long id) {
        Optional<Post> post = postRepo.findById(id);
        if (post.isEmpty()) {
            throw new RuntimeException("아이디가 없습니다");
        } else {
            return Optional.of(new PostDto.PostDeleteDto(post.get()));
        }
    }

    public boolean delete(PostDto.PostDeleteDto postDeleteDto) {

        Optional<Post> post = postRepo.findById(postDeleteDto.getId());

        AtomicBoolean check = new AtomicBoolean(false);

        post.ifPresent(m -> {
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

    public Page<Post> getPostList(Pageable pageable) {
        Page<Post> a = postRepo.findAll(pageable);
        return postRepo.findAll(pageable);
    }


    private static final int BLOCK_PAGE_NUM_COUNT = 5;
    private static final int PAGE_POST_COUNT = 5;

    public ArrayList<Integer> getPageList(Integer curPageNum) {
        ArrayList<Integer> pageList = new ArrayList<>();

        Double postsTotalCount = Double.valueOf(postRepo.count());

        Integer totalLastPageNum = (int) (Math.ceil((postsTotalCount / PAGE_POST_COUNT)));

        curPageNum = (curPageNum <= 2) ? 1 : (curPageNum - 1);

        Integer blockLastPageNum = (totalLastPageNum > (curPageNum + (BLOCK_PAGE_NUM_COUNT - 1))) ? (curPageNum + BLOCK_PAGE_NUM_COUNT - 1) : totalLastPageNum;

        for (int val = curPageNum; val <= blockLastPageNum; val++ ) {
            pageList.add(val);
        }

        return pageList;
    }
}
