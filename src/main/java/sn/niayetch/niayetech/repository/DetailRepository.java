package sn.niayetch.niayetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.niayetch.niayetech.entity.Commande;
import sn.niayetch.niayetech.entity.Detail;

import java.util.Optional;


@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    Optional<Detail> findByCommande(Commande commande);

}
