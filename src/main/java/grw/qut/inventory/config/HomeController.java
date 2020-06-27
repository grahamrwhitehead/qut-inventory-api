package grw.qut.inventory.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation
 */
@Controller
public class HomeController {
    @GetMapping(value = "/")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
