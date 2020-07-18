package com.example.cinema.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
public class Compte {
    @GeneratedValue @Id
    private Long Id;
    private String username;
    private String password;
    private byte active;

    @ManyToOne
    private Role role;
}
