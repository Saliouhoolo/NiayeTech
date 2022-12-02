package sn.niayetch.niayetech.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sn.niayetch.niayetech.entity.Produit;
import sn.niayetch.niayetech.entity.dto.FormProduit;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;

public interface ProduitService {
    List<Produit> getProduits();
    Produit updateProduit(@PathVariable(value="id") Long produitId, @RequestBody FormProduit produit);
    Produit getProduit(@PathVariable(value="id") Long produitId);
    Map<String ,String> createProduit(@RequestBody FormProduit produit) throws ValidationException;
    Map<String ,Boolean> deleteProduit(@PathVariable(value="id") Long produitId);

}
