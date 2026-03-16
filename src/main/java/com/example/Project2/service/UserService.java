package com.example.Project2.service;

import com.example.Project2.domain.User;
import com.example.Project2.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleCreate(){
        return "admin/user/create";
    }

    public String handleUpdate(){
        return "admin/user/update";
    }

    public String handleDelete(){
        return "admin/user/delete";
    }
    public String userCreatePage(){
        return "redirect:/admin/user";
    }

    public User userHandleSave(User user){
        return this.userRepository.save(user);
    }

    public List<User> getAllUser(){
        return this.userRepository.findAllByOrderByIdAsc();
    }

    public User GetAllUserById(long id){
        return this.userRepository.findById(id);
    }

    public String ViewTable(){
        return "admin/user/show";
    }

    public void userDelete(long id){
        this.userRepository.deleteById(id);
    }
}
