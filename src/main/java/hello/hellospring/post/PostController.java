package hello.hellospring.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("post")
public class PostController {
    final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("list")
    public String postboard(PostDto.PostSaveDto postSaveDto) {
        postService.save(new Post(postSaveDto));

        return "redirect:/post/list";
    }

    @GetMapping("list")
    public ModelAndView postboard2(){
        ModelAndView modelAndView = new ModelAndView();
        List<PostDto.PostListDto> posts =postService.findByAll();
        modelAndView.addObject("posts", posts);
        modelAndView.setViewName("postboard");
        return modelAndView;
    }

    @GetMapping("write")
    public ModelAndView postwrite(ModelAndView modelAndView) {

        modelAndView.setViewName("postwrite");
        return modelAndView;
    }

    @GetMapping(value = "content/{id}")
    public ModelAndView postcontent(@PathVariable Long id){
        Optional<PostDto.PostDetailDto> post= postService.findById(id);
        postService.view_Count(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post",post);
        modelAndView.setViewName("postcontents");
        return modelAndView;
    }


}
