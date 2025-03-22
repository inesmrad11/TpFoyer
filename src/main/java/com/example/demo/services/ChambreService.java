package com.example.demo.services;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.TypeChambre;
import com.example.demo.repositories.IChambreRepository;
//import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class ChambreService implements IChambreService{
    @Autowired
    IChambreRepository chambreRepository;
    @Override
    public List<Chambre> retrieveAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
        return chambreRepository.findById(idChambre).orElse(null);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        return chambreRepository.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, type);
    }

    // Solution 1: Using JPQL
    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.getChambresParBlocEtType(idBloc, typeC);
    }

    // Solution 2: Using Spring Data JPA Keywords
    @Override
    public List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC) {
        return chambreRepository.findByBlocIdBlocAndTypeC(idBloc, typeC);
    }

    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        return chambreRepository.findChambresByNomUniversite(nomUniversite);
    }
}
