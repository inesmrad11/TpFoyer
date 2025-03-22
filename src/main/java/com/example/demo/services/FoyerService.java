package com.example.demo.services;

import com.example.demo.entities.Bloc;
import com.example.demo.entities.Foyer;
import com.example.demo.entities.Universite;
import com.example.demo.repositories.IFoyerRepository;
//import lombok.AllArgsConstructor;
import com.example.demo.repositories.IUniversiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
public class FoyerService implements IFoyerService{
    @Autowired
    IFoyerRepository foyerRepository;
    @Autowired
    IUniversiteRepository universiteRepository;
    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        return foyerRepository.findById(idFoyer).orElse(null);
    }

    @Override
    public void removeFoyer(long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }

    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElseThrow(() -> new RuntimeException("Universite not found with ID: " + idUniversite));
        foyer.setUniversite(universite);
        for (Bloc bloc : foyer.getBlocs()) {
            bloc.setFoyer(foyer);
        }
        Foyer savedFoyer = foyerRepository.save(foyer);
        universite.setFoyer(savedFoyer);
        universiteRepository.save(universite);
        return savedFoyer;
    }
}
