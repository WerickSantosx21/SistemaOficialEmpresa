package back_end.front_end.SistemaOficialEmpresa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "home"; // renders src/main/resources/templates/home.html
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index"; // renders src/main/resources/templates/index.html
    }

    @PostMapping("/index")
    public String handleIndex(@RequestParam(required = false) String name,
                              @RequestParam(required = false) String email,
                              RedirectAttributes redirectAttributes) {

        if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Por favor preencha nome e e-mail.");
            redirectAttributes.addFlashAttribute("status", "error");
            return "redirect:/index";
        }

        redirectAttributes.addFlashAttribute("message", "Recebido: " + name + " <" + email + ">");
        redirectAttributes.addFlashAttribute("status", "success");
        return "redirect:/index";
    }
}
