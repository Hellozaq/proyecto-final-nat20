package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
    User findByName(String name);
    @Transactional
    User addRoleToUser(Long userId, Long roleId);
    @Transactional
    User removeRoleFromUser(Long userId, Long roleId);
    Optional<User> findByEmail(String email);
}
