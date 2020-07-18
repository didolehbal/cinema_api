package com.example.cinema.service;

import com.example.cinema.dao.*;
import com.example.cinema.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{
    @Autowired private VilleRepository villeRepository;
    @Autowired private CinemaRepository cinemaRepository;
    @Autowired private SalleRepository salleRepository;
    @Autowired private PlaceRepository placeRepository;
    @Autowired private SeanceRepository seanceRepository;
    @Autowired private FilmRepository filmRepository;
    @Autowired private ProjectionRepository projectionRepository;
    @Autowired private CategorieRepository categorieRepository;
    @Autowired private TicketRepository ticketRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private CompteRepository compteRepository;


    @Override
    public void initVilles() {
        Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(nameVille->{
            Ville ville = new Ville();
            ville.setName(nameVille);
            ville.setAltitude(Math.random());
            ville.setLatitude(Math.random());
            ville.setLongitude(Math.random());
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> Stream.of("Mega Rama","IMAX","FOUNOUN","CHAHRAZAD","DAOULIZ").forEach(nameCinema->{
                Cinema cinema = new Cinema();
                cinema.setName(nameCinema);
                cinema.setNombreSalles(3+(int)(Math.random()*7));
                cinema.setVille(ville);
                cinema.setAltitude(Math.random());
                cinema.setLatitude(Math.random());
                cinema.setLongitude(Math.random());
                cinemaRepository.save(cinema);
            }
        ));
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNombreSalles(); i++) {
                Salle salle = new Salle();
                salle.setName("Salle "+(i+1));
                salle.setCinema(cinema);
                salle.setNombrePlace(15+(int)(Math.random()*20));
                salleRepository.save(salle);
            }
        });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNombrePlace(); i++) {
                Place place = new Place();
                place.setNumero(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s -> {
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Histoire","Actions","Fiction","Drama").forEach(cat->{
           Categorie categorie = new Categorie();
           categorie.setName(cat);
           categorieRepository.save(categorie);
        });
    }

    @Override
    public void initFilms() {
        double[] durees = new double[] {1,1.5,2,2.5,3};
        List<Categorie> categories = categorieRepository.findAll();
        Stream.of("12 angry birds","Forrest Gump","Green Book","The God Father","Lord of rings").forEach(titreFilm -> {
            Film film = new Film();
            film.setTitre(titreFilm);
            film.setDuree(durees[new Random().nextInt(durees.length)]);
            film.setPhoto(titreFilm.replaceAll(" ","")+".jpg");
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);
        });
    }

    @Override
    public void initProjections() {
        double[] prices = new double[]{30,50,60,70,90,100};
        List<Film> films = filmRepository.findAll();
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index= new Random().nextInt(films.size());
                    Film film = films.get(index);
                    seanceRepository.findAll().forEach(seance -> {
                        Projection projection = new Projection();
                        projection.setFilm(film);
                        projection.setPrix(prices[new Random().nextInt(prices.length)]);
                        projection.setSalle(salle);
                        projection.setSeance(seance);
                        projectionRepository.save(projection);
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projection ->
            projection.getSalle().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setCodePayement(0);
                ticket.setPrix(projection.getPrix());
                ticket.setProjection(projection);
                ticket.setReserve(false);
                ticketRepository.save(ticket);
            }
        ));
    }

    @Override
    public void initRoles() {
        Role role1 = new Role();
        role1.setRole("ADMIN");
        roleRepository.save(role1);
        System.out.println("admin \n");
    }

    @Override
    public void initComptes() {
        Compte compte1 = new Compte();
        compte1.setUsername("admin");
        compte1.setPassword("$2a$10$uvnSJ6mHxBya9hMs7GdYH.9CSzocPXlJk58NnfbxBhdFe5AW0dhIq");
        compte1.setRole(roleRepository.findByRole("ADMIN"));
        compte1.setActive((byte) 1);
        compteRepository.save(compte1);
//
//        Compte compte2 = new Compte();
//        compte2.setUsername("prof");
//        //pass : prof
//        compte2.setPassword("$2a$10$YCYMAwqcHNX4o2X6zrpD3.brmiN945uN6hLwc2fe1ZOgTQvgSQcwi");
//        compte2.setRole(roleRepository.getOne((long) 2));
//        compte2.setActive((byte) 1);
//        compteRepository.save(compte2);
//
//        Compte compte3 = new Compte();
//        compte3.setUsername("etud");
//        //pass : etud
//        compte3.setPassword("$2a$10$cAmHAFW.IQc8fCBEaa7Lg.bNAimNdOok0/GFTcstwXu.0SzhZxcze");
//        compte3.setRole(roleRepository.getOne((long) 3));
//        compte3.setActive((byte) 1);
//        compteRepository.save(compte3);
    }
}
