package com.example.demo.repositories;

import com.example.demo.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEtudiantRepository extends JpaRepository<Etudiant,Long> {
    Optional<Etudiant> findByCin(long cin);
}
