package com.example.demo.repositories;

import com.example.demo.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation,String> {
}
