package guru.springframework.springrecipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author kas
 */
@Controller
public class IndexController {

    @GetMapping({"", "/", "/index"})
    public String getIndexPage() {
        System.out.println("lad");
        return "index";
    }
}
