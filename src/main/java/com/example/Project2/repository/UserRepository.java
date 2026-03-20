package com.example.Project2.repository;


import com.example.Project2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User in4mation);

    List<User> findByEmailAndPassword(String email, String password);

    List<User> findByEmail(String email);

    List<User> findAllByOrderByIdAsc();

    User findById(long id);

    void deleteById(long id);

    boolean existsUserByEmail(String email);

    User getUserByEmail(String email);
}


