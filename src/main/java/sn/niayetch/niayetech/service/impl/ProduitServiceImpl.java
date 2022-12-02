package sn.niayetch.niayetech.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sn.niayetch.niayetech.entity.Category;
import sn.niayetch.niayetech.entity.Produit;
import sn.niayetch.niayetech.entity.User;
import sn.niayetch.niayetech.entity.dto.FormProduit;
import sn.niayetch.niayetech.repository.CategoryRepository;
import sn.niayetch.niayetech.repository.ProduitRepository;
import sn.niayetch.niayetech.repository.UserRepository;
import sn.niayetch.niayetech.service.ProduitService;
import sn.niayetch.niayetech.util.FileDelete;

import javax.xml.bind.ValidationException;
import java.lang.module.ResolutionException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class ProduitServiceImpl implements ProduitService {
    private ProduitRepository produitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public List<Produit> getProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit updateProduit(Long produitId, FormProduit produit) {
        Optional<Produit> produitOptional = produitRepository.findById(produitId);
        if(produitOptional.isEmpty()){
            throw new ResolutionException("Produit not found");
        }
        Optional<Category> categoryOptional = this.categoryRepository.findById(produit.getCategory());
        Category category = categoryOptional.get();
        Produit produit1 = produitOptional.get();
        produit1.setLibelle(produit.getLibelle());
        produit1.setDescription(produit.getDescription());
        produit1.setQte(produit.getQte());
        produit1.setPrix(produit.getPrix());
        produit1.setImage(produit.getImage());
        produit1.setCategory(category);
        produitRepository.save(produit1);
        return produit1;
    }

    @Override
    public Produit getProduit(Long produitId) {
        Optional<Produit> produitOptional = produitRepository.findById(produitId);
        if(produitOptional.isEmpty()){
            throw new ResolutionException("Produit not found");
        }
        Produit produit = produitOptional.get();

        return produit;
    }

    @Override
    public Map<String, String> createProduit(FormProduit produit) throws ValidationException {
        if(produitRepository.findByLibelle(produit.getLibelle()).isPresent()){
            throw new ValidationException("Produit exists");
        }
        User user1 = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> userOptional = userRepository.findByEmail(user1.getEmail());
        User user = userOptional.get();
        Optional<Category> categoryOptional = this.categoryRepository.findById(produit.getCategory());
        Category category = categoryOptional.get();
        Produit produit1 = new Produit();
        produit1.setLibelle(produit.getLibelle());
        produit1.setDescription(produit.getDescription());
        produit1.setQte(produit.getQte());
        produit1.setPrix(produit.getPrix());
        produit1.setImage(produit.getImage());
        produit1.setCategory(category);
        produit1.setCreatedBy(user);

        produitRepository.save(produit1);
        Map<String , String> response = new HashMap<>();
        response.put("message", "Ajout produit réuissie");
        return response;
    }

    @Override
    public Map<String, Boolean> deleteProduit(Long produitId) {
        Optional<Produit> produitOptional = produitRepository.findById(produitId);
        Map<String , Boolean> response = new HashMap<>();
        if(produitOptional.isEmpty()){
            response.put("delete", Boolean.FALSE);
        } else{
            response.put("delete", Boolean.TRUE);
            Produit produit = produitOptional.get();
            FileDelete.delete(produit.getImage());
            produitRepository.delete(produit);
        }

        return response ;
    }

}
