package sn.niayetch.niayetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.niayetch.niayetech.entity.Produit;
import sn.niayetch.niayetech.entity.User;

import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    Optional<Produit> findByLibelle(String libelle);

}
