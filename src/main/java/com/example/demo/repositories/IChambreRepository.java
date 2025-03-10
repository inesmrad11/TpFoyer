package com.example.demo.repositories;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChambreRepository extends JpaRepository<Chambre, Long> {
    @Query("SELECT c FROM Chambre c " +
            "JOIN c.bloc b " +
            "JOIN b.foyer f " +
            "JOIN f.universite u " +
            "WHERE u.nomUniversite = :nomUniversite " +
            "AND c.typeC = :type " +
            "AND (c.reservations IS EMPTY " +
            "OR NOT EXISTS (" +
            "    SELECT r FROM c.reservations r " +
            "    WHERE r.anneeUniversitaire = CURRENT_DATE " +
            "    AND r.estValide = true" +
            "))")
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@Param("nomUniversite") String nomUniversite, @Param("type") TypeChambre type);

    //Solution 1: Using JPQL
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> getChambresParBlocEtType(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);

    //Solution 2: Using Spring Data JPA Keywords
    List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC);
}
