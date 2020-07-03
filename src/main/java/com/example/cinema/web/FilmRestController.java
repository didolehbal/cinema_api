package com.example.cinema.web;

import com.example.cinema.dao.FilmRepository;
import com.example.cinema.dao.TicketRepository;
import com.example.cinema.entities.Film;
import com.example.cinema.entities.Ticket;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class FilmRestController {
    @Autowired private FilmRepository filmRepository;
    @Autowired private TicketRepository ticketRepository;

    @GetMapping(path = "/imageFilm/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable (name="id")Long id) throws Exception{
        Film f = filmRepository.findById(id).get();
        String photoName = f.getPhoto();
        File file = new File(System.getProperty("user.home")+"/cinema/images/"+photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketFrom ticketFrom){
        List<Ticket> ticketList = new ArrayList<>();
        ticketFrom.getTickets().forEach(id->{
            Ticket ticket = ticketRepository.findById(id).get();
            ticket.setNomClient(ticketFrom.getNomClient());
            ticket.setReserve(true);
            ticket.setCodePayement(ticketFrom.getCodePayement());
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;
    }
}

@Data
class TicketFrom{
    private String nomClient;
    private int codePayement;
    private List<Long> tickets = new ArrayList<>();
}