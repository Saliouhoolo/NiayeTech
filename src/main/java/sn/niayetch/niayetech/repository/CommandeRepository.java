package sn.niayetch.niayetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.niayetch.niayetech.entity.Commande;


@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
