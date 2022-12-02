package sn.niayetch.niayetech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sn.niayetch.niayetech.entity.Commande;
import sn.niayetch.niayetech.entity.Detail;
import sn.niayetch.niayetech.entity.Produit;
import sn.niayetch.niayetech.entity.User;
import sn.niayetch.niayetech.entity.dto.FormCommande;
import sn.niayetch.niayetech.repository.CommandeRepository;
import sn.niayetch.niayetech.repository.DetailRepository;
import sn.niayetch.niayetech.repository.ProduitRepository;
import sn.niayetch.niayetech.repository.UserRepository;
import sn.niayetch.niayetech.service.CommandeService;

import java.lang.module.ResolutionException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CommandeServiceImpl implements CommandeService {
    private CommandeRepository commandeRepository;
    @Autowired
    private DetailRepository detailRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private UserRepository userRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public Map<String, String> add(FormCommande formCommande) {
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByEmail(user1.getEmail());
        User user = userOptional.get();

        Commande commande = new Commande();
        commande.setDate(Instant.now());
        commande.setStatus(0);
        commande.setTotal(formCommande.getTotal());
        commande.setUser(user);
        commandeRepository.save(commande);
        for (Produit produit : formCommande.getProduits()) {
            Detail detail = new Detail(produit.getPrix(),produit.getQte(),produit,commande);
            detailRepository.save(detail);
        }

        Map<String , String> response = new HashMap<>();
        response.put("message", " commande  envoyée avec succés");
        return response;
    }

    @Override
    public List<Commande> all() {
        return commandeRepository.findAll();
    }

    @Override
    public List<Detail> findDetails(int id) {
        Optional<Commande> commandeOptional = commandeRepository.findById((long) id);
        if(commandeOptional.isEmpty()){
            throw new ResolutionException("Commande not found");
        }
        Commande commande1 = commandeOptional.get();
        Optional<Detail> details = detailRepository.findByCommande(commande1);
        Detail details1 = details.get();
         return (List<Detail>) details1;
    }

    @Override
    public Map<String, String> changeStatus(int id) {
        Optional<Commande> commandeOptional = commandeRepository.findById((long) id);
        if(commandeOptional.isEmpty()){
            throw new ResolutionException("Commande not found");
        }
        Commande commande1 = commandeOptional.get();
        commande1.setStatus(1);
        Map<String , String> response = new HashMap<>();
        response.put("message", " commande  maquée comme livré");
        return response;
    }
}
