package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    Optional<Notification> findById(Long id);
    List<Notification> findAll();
    Optional<Notification> save(Notification notification);
    void deleteById(Long id);
}
