package com.example.demo.services;

import com.example.demo.entities.Foyer;

import java.util.List;

public interface IFoyerService {
    List<Foyer> retrieveAllFoyers();
    Foyer addFoyer(Foyer f);
    Foyer updateFoyer(Foyer f);
    Foyer retrieveFoyer(long idFoyer);
    void removeFoyer(long idFoyer);
}
