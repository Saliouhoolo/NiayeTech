package sn.niayetch.niayetech.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FormProduit {
    private String libelle;
    private String description;
    private String image;
    private int qte;
    private int prix;
    private Long category;

}
