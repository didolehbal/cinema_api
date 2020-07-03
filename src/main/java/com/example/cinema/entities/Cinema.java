package com.example.cinema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data @Entity @AllArgsConstructor @NoArgsConstructor
public class Cinema implements Serializable {
    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private double longitude,latitude,altitude;
    private int nombreSalles;
    @OneToMany(mappedBy = "cinema")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Salle> salles;
    @ManyToOne
    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Ville ville;
}
