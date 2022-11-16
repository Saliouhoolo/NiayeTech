package sn.niayetch.niayetech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.niayetch.niayetech.entity.Category;
import sn.niayetch.niayetech.entity.User;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<User> findByLibelle(String libelle);
}
