package com.example.Project2.web.Mapper;

import com.example.Project2.domain.Role;
import com.example.Project2.domain.User;
import com.example.Project2.domain.dto.RegisterDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MapperDTO {
    private PasswordEncoder passwordEncoder;

    public MapperDTO(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());

        Role role = new Role();
        role.setId(2);
        user.setRole(role);

        String hashPassword = this.passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(hashPassword);

        return user;
    }
}
