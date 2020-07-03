package com.example.cinema.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "tProj", types=Ticket.class)
public interface TicketProjection {
    public Long getId();
    public String getNomClient();
    public double getPrix();
    public Integer getCodePayement();
    public boolean getReserve();
    public Place getPlace();
}
