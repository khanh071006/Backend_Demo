package com.example.Project2.web.admin;

import com.example.Project2.domain.User;
import com.example.Project2.service.UploadService;
import com.example.Project2.service.UserService;
import jakarta.servlet.ServletContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private UploadService uploadService;
    private PasswordEncoder passwordEncoder;


    public UserController(UserService userService, UploadService uploadService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/user/create")
    public String handleCreate(Model model) {
        model.addAttribute("newUser", new User());
        return this.userService.handleCreate();
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User output, @RequestParam("imageFile") MultipartFile file) {
        String finalFile = this.uploadService.handleSaveUploadService(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(output.getPassword());

        output.setPassword(hashPassword);
        output.setAvatar(finalFile);
        this.userService.userHandleSave(output);
        return this.userService.userCreatePage();

    }

    @GetMapping("/admin/user")
    public String viewTable(Model model) {
        List<User> persons = this.userService.getAllUser();
        model.addAttribute("persons", persons);
        return this.userService.ViewTable();
    }

    @GetMapping("/admin/users/{id}")
    public String ViewDetailUser(Model model, @PathVariable long id) {
        System.out.println(id);
        User dataView = this.userService.GetAllUserById(id);
        model.addAttribute("user", dataView);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/update/{id}")
    public String UpdateUser(Model model, @PathVariable long id) {
        User currentUser = this.userService.GetAllUserById(id);

        model.addAttribute("newUser", currentUser);

        return this.userService.handleUpdate();
    }

    @PostMapping("/admin/user/update")
    public String updateUser(Model model, @ModelAttribute("newUser") User output) {
        User currentUser = this.userService.GetAllUserById(output.getId());
        currentUser.setAddress(output.getAddress());
        currentUser.setFullName(output.getFullName());
        currentUser.setPhone(output.getPhone());

        this.userService.userHandleSave(currentUser);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String DeleteUser(Model model, @PathVariable long id) {
        User currentUser = this.userService.GetAllUserById(id);

        model.addAttribute("newUser", currentUser);

        return this.userService.handleDelete();
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(Model model, @ModelAttribute("newUser") User output) {
        this.userService.userDelete(output.getId());
        return "redirect:/admin/user";
    }

}
