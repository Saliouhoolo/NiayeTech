package sn.niayetch.niayetech.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "libelle",nullable = false)
    private String libelle;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "qte",nullable = false)
    private int qte;
    @Column(name = "prix",nullable = false)
    private int prix;

    @ManyToOne
    @JoinColumn(name="category")
    @JsonIgnoreProperties("category")
    private Category category;
    @ManyToOne
    @JoinColumn(name="createdBy")
    @JsonIgnoreProperties("user")
    private User createdBy;
}
