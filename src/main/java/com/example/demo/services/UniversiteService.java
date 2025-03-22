package com.example.demo.services;

import com.example.demo.entities.Foyer;
import com.example.demo.entities.Universite;
import com.example.demo.repositories.IFoyerRepository;
import com.example.demo.repositories.IUniversiteRepository;
//import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class UniversiteService implements IUniversiteService{
    @Autowired
    IUniversiteRepository universiteRepository;
    IFoyerRepository foyerRepository;
    @Override
    public List<Universite> retrieveAllUniversities() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {
        return universiteRepository.findById(idUniversite).orElse(null);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElseThrow(() -> new RuntimeException("Foyer not found with ID: " + idFoyer));
        Universite universite = universiteRepository.findUniversiteByNomUniversite(nomUniversite);
        if (universite == null) {
            throw new RuntimeException("Universite not found with name: " + nomUniversite);
        }
        universite.setFoyer(foyer);
        foyer.setUniversite(universite);
        universiteRepository.save(universite);
        foyerRepository.save(foyer);
        return universite;
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElseThrow(() -> new RuntimeException("Universite not found with ID: " + idUniversite));
        Foyer foyer = universite.getFoyer();
        if (foyer != null) {
            foyer.setUniversite(null);
            foyerRepository.save(foyer);
        }
        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }
}
