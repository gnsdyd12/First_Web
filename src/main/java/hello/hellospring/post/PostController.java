package hello.hellospring.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        modelAndView.addObject("post",post.get());
        modelAndView.setViewName("postcontents");
        return modelAndView;
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView postModify(@PathVariable Long id){
        Optional<PostDto.PostModifyDto> post = postService.findByIdForModify(id);
        //postService.checkPw(post.get());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post",post.get());
        modelAndView.setViewName("PostModify");
        return modelAndView;
    }

    @PostMapping("modify/{id}")
    public String postModify2(PostDto.PostModifyDto postModifyDto, Model model){
        boolean result;
        result= postService.modify(postModifyDto);
        if(!result){
            model.addAttribute("post",postModifyDto);
            model.addAttribute("pwNot","비밀번호가 틀립니다.");
            return "PostModify";
        }
        return "redirect:/post/content/"+postModifyDto.getId();
    }
//    @PutMapping("Notmodify")
//    public String postModify3(PostDto.PostModifyDto postModifyDto,Model model){
//        boolean result;
//        result= postService.modify(postModifyDto);
//        if(!result){
//            model.addAttribute("pwNot","비밀번호가 틀립니다.");
//            return "redirect:/post/modify/"+postModifyDto.getId();
//        }
//    }

    @GetMapping(value = "delete/{id}")
    public String postDelete(@PathVariable Long id){
        Optional<PostDto.PostDeleteDto> post =postService.findByIdForDelete(id);
        postService.delete(post.get().getId());
        return "redirect:/post/list";
    }
}
