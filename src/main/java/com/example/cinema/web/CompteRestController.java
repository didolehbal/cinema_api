package com.example.cinema.web;

import com.example.cinema.dao.CompteRepository;
import com.example.cinema.dao.RoleRepository;
import com.example.cinema.entities.Compte;
import com.example.cinema.entities.Role;
import com.example.cinema.service.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompteRestController {
    @Autowired private CompteRepository compteRepository;
    @Autowired private RoleRepository roleRepository;

    @PostMapping("/signup")
    public Compte signUp(@RequestBody RegistrationForm data){
        String username = data.getUserName();
        Compte compte = compteRepository.findByUsername(username);
        if (compte!=null)
            throw new RuntimeException("THis user already exist ... ");
        String password = data.getPassword();
        String repassword = data.getRepassword();
        if (!password.equals(repassword))
            throw new RuntimeException("password incorrect");
        Compte c = new Compte();
        c.setUsername(username);
        c.setPassword(password);
        Role role = roleRepository.findByRole("ADMIN");
        c.setRole(role);
        compteRepository.save(c);
        return c;
    }

    @GetMapping(value = "/listComptes")
    public List<Compte> listCompte() {
        return compteRepository.findAll();
    }

    @GetMapping(value = "GetCompte/{id}")
    public Compte listCompte(@PathVariable(name = "id") Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    @PutMapping(value = "UpdateCompte/{id}")
    public Compte Update(@PathVariable(name = "id") Long id, @RequestBody Compte e) {
        e.setId(id);
        return compteRepository.save(e);
    }

    @PostMapping(value = "CreateCompte/")
    public Compte save(@RequestBody Compte e) {
        return compteRepository.save(e);
    }

    @DeleteMapping(value = "DeleteCompte/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        compteRepository.deleteById(id);
    }

}
