package com.example.cinema.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @GeneratedValue @Id
    private Long Id;
    private String role;

    @OneToMany(mappedBy = "role")
    private Collection<Compte> comptes;

}
