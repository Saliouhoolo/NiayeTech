package sn.niayetch.niayetech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.niayetch.niayetech.entity.Commande;
import sn.niayetch.niayetech.entity.Detail;
import sn.niayetch.niayetech.entity.dto.FormCommande;
import sn.niayetch.niayetech.service.CommandeService;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;

/**
 *  - GET /api/commande
 *
 * 	- POST /api/commande
 *
 * 	- GET /api/commande/{id}
 *
 * 	- PUT /api/commande/{id}
 *
 * 	- DELETE /api/commande/{id}
 */
@RestController
@RequestMapping("/api")
public class CommandeController {
    private final CommandeService commandeService;
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping("/commande")
    public List<Commande> getCategories(){
        return commandeService.all();
    }
    @GetMapping("/change/{id}")
    public Map<String, String> change(@PathVariable(value="id") int commandeId){
        return commandeService.changeStatus(commandeId);
    }
    @GetMapping("/detail/{id}")
    public List<Detail> findDetails(@PathVariable(value="id") int commandeId){
        return commandeService.findDetails(commandeId);
    }
    @PostMapping("/commande")
    public Map<String, String> addCommande(@RequestBody FormCommande formCommande)  {
        return commandeService.add(formCommande);
    }
}
