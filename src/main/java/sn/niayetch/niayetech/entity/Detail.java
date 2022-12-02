package sn.niayetch.niayetech.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data @Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "prix")
    private int prix;
    @Column(name = "qte")
    private int qte;
    @ManyToOne
    @JoinColumn(name = "produit")
    @JsonIgnoreProperties("produit")
    private Produit produit;
    @ManyToOne
    @JoinColumn(name = "commande")
    @JsonIgnoreProperties("commande")
    private Commande commande;

    public Detail(int prix, int qte, Produit produit, Commande commande) {
        this.prix = prix;
        this.qte = qte;
        this.produit = produit;
        this.commande = commande;
    }
}
