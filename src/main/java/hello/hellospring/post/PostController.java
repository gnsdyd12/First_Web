package hello.hellospring.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
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
    public ModelAndView postboard2(ModelAndView modelAndView,
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                   @RequestParam(value="page", defaultValue = "0")Integer pageNum) {
        modelAndView.addObject("postboard", postService.getPostList(pageable));

        ArrayList pageList =postService.getPageList(pageNum);
        modelAndView.addObject("pageable", pageable);
        modelAndView.addObject("pageList",pageList);
        modelAndView.setViewName("postboard");
        return modelAndView;
    }

    @GetMapping("write")
    public ModelAndView postwrite(ModelAndView modelAndView) {

        modelAndView.setViewName("postwrite");
        return modelAndView;
    }

    @GetMapping(value = "content/{id}")
    public ModelAndView postcontent(@PathVariable Long id) {
        Optional<PostDto.PostDetailDto> post = postService.findById(id);
        postService.view_Count(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", post.get());
        modelAndView.setViewName("postcontents");
        return modelAndView;
    }

    @GetMapping(value = "modify/{id}")
    public ModelAndView postModify(@PathVariable Long id) {
        PostDto.PostModifyDto post = postService.findByIdForModify(id).get();
        //postService.checkPw(post.get());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", post);
        modelAndView.setViewName("PostModify");
        return modelAndView;
    }

    @PostMapping("modify/{id}")
    public String postModify2(PostDto.PostModifyDto postModifyDto, Model model) {
        boolean result;
        result = postService.modify(postModifyDto);
        if (!result) {
            model.addAttribute("post", postModifyDto);
            model.addAttribute("pwNot", "비밀번호가 틀립니다.");
            return "PostModify";
        }
        return "redirect:/post/content/" + postModifyDto.getId();
    }

    @GetMapping(value = "fordelete/{id}")
    public ModelAndView postPwForDelete(@PathVariable Long id) {
        Optional<PostDto.PostDeleteDto> post = postService.findByIdForDelete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("post", post.get());
        modelAndView.setViewName("postPwForDelete");
        return modelAndView;
    }

    @PostMapping(value = "delete/{id}")
    public String postDelete(PostDto.PostDeleteDto postDeleteDto, Model model) {
        //삭제
        boolean result;
        result = postService.delete(postDeleteDto);
        if (!result) {
            model.addAttribute("post", postDeleteDto);
            model.addAttribute("pwNot", "비밀번호가 틀립니다.");
            return "postPwForDelete";
        }
        return "redirect:/post/list";
    }
}
