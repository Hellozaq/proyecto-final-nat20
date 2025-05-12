package org.example.proyectofinalnat20.repository;

import org.example.proyectofinalnat20.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
