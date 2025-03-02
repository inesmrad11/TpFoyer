package com.example.demo.services;

import com.example.demo.entities.Chambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();

    Chambre  addChambre(Chambre c);

    Chambre updateChambre (Chambre  c);

    Chambre retrieveChambre (long idChambre);
}
