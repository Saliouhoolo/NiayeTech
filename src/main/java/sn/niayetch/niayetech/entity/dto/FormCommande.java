package sn.niayetch.niayetech.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.niayetch.niayetech.entity.Produit;

import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class FormCommande {
    private int total;
    private String adresse;
    private String tel;
    private List<Produit> produits;
}
