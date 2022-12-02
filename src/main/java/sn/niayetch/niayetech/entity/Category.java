package sn.niayetch.niayetech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categories")
public class Category implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "libelle",nullable = false)
    private String libelle;
    @JsonIgnore
    @OneToMany(mappedBy="category")
    private Set<Produit> produit;

    @Override
    public String toString() {
        return  libelle ;
    }
}
