package com.example.demo.repositories;

import com.example.demo.entities.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChambreRepository extends JpaRepository<Chambre,Long> {
}
