package com.example.cinema.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;


@Data @Entity @NoArgsConstructor @AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue
    private Long id;
    private String titre,description,realisateur,photo;
    private double duree;
    private Date dateSortie;

    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;

    @ManyToOne @JoinColumn(columnDefinition = "id_categorie")
    private Categorie categorie;
}
