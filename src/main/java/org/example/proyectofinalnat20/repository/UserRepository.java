package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}
