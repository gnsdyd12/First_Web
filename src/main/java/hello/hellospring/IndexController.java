package hello.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("index")
    public ModelAndView index(ModelAndView modelAndView) {
        //model.addAttribute("data","hello!!");
        modelAndView.setViewName("index");
        //modelAndView.addObject("data", "spring");
        return modelAndView;
    }

    @GetMapping("jpop")
    public ModelAndView jpop(ModelAndView modelAndView) {
        //model.addAttribute("data","hello!!");
        modelAndView.setViewName("jpop");
        //modelAndView.addObject("data", "spring");
        return modelAndView;
    }

    @GetMapping("kpop")
    public ModelAndView kpop(ModelAndView modelAndView) {

        modelAndView.setViewName("kpop");

        return modelAndView;
    }

    @GetMapping("pop")
    public ModelAndView pop(ModelAndView modelAndView) {

        modelAndView.setViewName("pop");

        return modelAndView;
    }
}
