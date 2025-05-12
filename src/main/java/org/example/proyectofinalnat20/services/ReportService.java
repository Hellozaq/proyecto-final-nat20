package org.example.proyectofinalnat20.services;

import org.example.proyectofinalnat20.entity.Report;

import java.util.Optional;

public interface ReportService {

    Optional<Report> findById(Long id);
    Optional<Report> save(Report report);
    void deleteById(Long id);
    Optional<Report> findByName(String name);
    Optional<Report> findByDescription(String description);
    Boolean existsById(Long id);

}
