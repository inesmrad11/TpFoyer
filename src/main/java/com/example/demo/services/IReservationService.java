package com.example.demo.services;

import com.example.demo.entities.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservation();

    Reservation updateReservation (Reservation  res);

    Reservation retrieveReservation (String idReservation);
}
