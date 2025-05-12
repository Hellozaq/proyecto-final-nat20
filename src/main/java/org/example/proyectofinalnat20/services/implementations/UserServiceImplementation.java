package org.example.proyectofinalnat20.services.implementations;

import org.example.proyectofinalnat20.entity.Role;
import org.example.proyectofinalnat20.entity.User;
import org.example.proyectofinalnat20.repository.RoleRepository;
import org.example.proyectofinalnat20.repository.UserRepository;
import org.example.proyectofinalnat20.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User save(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser != null) {
            throw new RuntimeException("User already exists with email: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)){
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new RuntimeException("User not found with username: " + name));
    }

    /**
     * Add a role to a user
     * @param userId The ID of the user
     * @param roleId The ID of the role to add
     * @return The updated user
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public User addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        user.setRole(role);
        return user;
    }

    /**
     * Remove a role from a user
     * @param userId The ID of the user
     * @param roleId The ID of the role to remove
     * @return The updated user
     */
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public User removeRoleFromUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found with id: " + roleId));
        if (user.getRole().equals(role)){
            user.setRole(null);
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
