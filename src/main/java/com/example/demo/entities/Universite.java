package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "nomUniversite")})
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversite;
    @Column(unique = true)
    private String nomUniversite;
    private String adresse;

    @OneToOne(mappedBy = "universite", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Foyer foyer;
}
