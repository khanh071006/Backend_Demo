package com.example.Project2.web.client;


import com.example.Project2.domain.User;
import com.example.Project2.domain.dto.RegisterDTO;
import com.example.Project2.service.UserService;
import com.example.Project2.web.Mapper.MapperDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private MapperDTO mapperDTO;
    private UserService userService;

    public RegisterController(MapperDTO mapperDTO, UserService userService) {
        this.mapperDTO = mapperDTO;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerInfor", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    private String finishRegister(Model model, @ModelAttribute("registerInfor") RegisterDTO registerInfor) {
        User user = this.mapperDTO.registerDTOtoUser(registerInfor);
        this.userService.userHandleSave(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "client/auth/login";
    }
}
