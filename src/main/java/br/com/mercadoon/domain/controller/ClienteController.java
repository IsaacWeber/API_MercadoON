package br.com.mercadoon.domain.controller;

import br.com.mercadoon.api.dto.ClienteDto;
import br.com.mercadoon.api.exception.LoginException;
import br.com.mercadoon.domain.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    private LoginService loginService;

    public ClienteController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/cadastrar")
    public String cadastrar() {
        return "cadastro_cliente";
    }

    @GetMapping("/cadastrado")
    public String cadastrado() {
        return "cliente_cadastrado";
    }

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute("email") String email,
                              @ModelAttribute("erro_msg") String erroMsg,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {

        if(session.getAttribute("cliente") != null) {
            redirectAttributes.addFlashAttribute("cliente", session.getAttribute("cliente"));
            return new ModelAndView("redirect:/cliente/home");
        }

        ModelAndView mv = new ModelAndView("login");
        mv.addObject("email", email);
        mv.addObject("erro_msg", erroMsg);
        return mv;
    }

    @GetMapping("/home")
    public ModelAndView home(@ModelAttribute("cliente") ClienteDto cliente) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("entrada_cliente");
        mv.addObject("cliente", cliente);

        return mv;
    }

    @PostMapping("/fazer_login")
    public ModelAndView fazerLogin(@RequestParam("email") String email,
                                   @RequestParam("senha") String senha,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView();

        try {
            ClienteDto clienteDto = loginService.fazerLogin(email, senha);
            session.setAttribute("cliente", clienteDto);

            mv.setViewName("redirect:/cliente/home");
            redirectAttributes.addFlashAttribute("cliente", clienteDto);
        }catch (LoginException e) {
            mv.setViewName("redirect:/cliente/login");
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("erro_msg", e.getMessage());
        }

        return mv;
    }

    @GetMapping("/sair")
    public ModelAndView sair(HttpSession session) {
        session.setAttribute("cliente", null);

        return new ModelAndView("redirect:/");
    }
}
