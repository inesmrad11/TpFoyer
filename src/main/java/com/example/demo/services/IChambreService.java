package com.example.demo.services;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.TypeChambre;

import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();

    Chambre  addChambre(Chambre c);

    Chambre updateChambre (Chambre  c);

    Chambre retrieveChambre (long idChambre);
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);
    // Solution 1: Using JPQL
    List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) ;
    // Solution 2: Using Spring Data JPA Keywords
    List<Chambre> findByBlocIdBlocAndTypeC (long idBloc, TypeChambre typeC) ;
    List<Chambre> getChambresParNomUniversite(String nomUniversite);
}
