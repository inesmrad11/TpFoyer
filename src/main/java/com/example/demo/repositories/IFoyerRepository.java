package com.example.demo.repositories;

import com.example.demo.entities.Foyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoyerRepository extends JpaRepository<Foyer,Long> {
}
