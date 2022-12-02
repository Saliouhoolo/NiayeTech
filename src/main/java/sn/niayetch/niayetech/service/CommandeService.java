package sn.niayetch.niayetech.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import sn.niayetch.niayetech.entity.Commande;
import sn.niayetch.niayetech.entity.Detail;
import sn.niayetch.niayetech.entity.dto.FormCommande;

import java.util.List;
import java.util.Map;

public interface CommandeService {
    Map<String ,String> add(@RequestBody FormCommande formCommande);
    List<Commande> all();
    List<Detail> findDetails(@PathVariable(value="id") int id);
    Map<String ,String> changeStatus(@RequestBody int id);

}
