package back_end.front_end.SistemaOficialEmpresa;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    private final String validUser = "Werick Santos";
    private final String validPass = "werick123";

    // Página de login (GET)
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // retorna login.html
    }

    // Processa o login (POST)
    @PostMapping("/login")
    public String handleLogin(@RequestParam String user,
                              @RequestParam String pass,
                              RedirectAttributes redirectAttributes) {
        if (user.equals(validUser) && pass.equals(validPass)) {
            // Login correto, redireciona para a pagina princal q e o /index
            return "redirect:/index";
        } else {
            // Login incorreto, volta para a pagina login com mensagem de erro
            redirectAttributes.addFlashAttribute("message", "Usuário ou senha incorretos.");
            redirectAttributes.addFlashAttribute("status", "error");
            return "redirect:/login";
        }
    }

    // Página inicial depois do login
    @GetMapping("/")
    public String home() {
        return "home"; // home.html
    }

    @GetMapping("/index")
    public String index(Model model) {
        return "index"; // index.html
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
