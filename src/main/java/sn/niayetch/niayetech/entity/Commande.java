package sn.niayetch.niayetech.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Data @Getter @Setter
@Entity
@Table(name = "commandes")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "date")
    private Instant date;
    @Column(name = "status")
    private int status;
    @Column(name = "adresse")
    private String adress;
    @Column(name = "tel")
    private String tel;
    @Column(name = "total")
    private int total;
    @ManyToOne
    @JoinColumn(name = "commande")
    @JsonIgnoreProperties("user")
    private User user;

 /*   @JsonIgnore
    @OneToMany(mappedBy="commande")
    private Set<Detail> details;*/
}
