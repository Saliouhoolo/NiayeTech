package sn.niayetch.niayetech.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sn.niayetch.niayetech.entity.Produit;
import sn.niayetch.niayetech.entity.dto.FormProduit;
import sn.niayetch.niayetech.service.ProduitService;
import sn.niayetch.niayetech.util.FileUploadUtil;

import javax.annotation.security.RolesAllowed;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *  - GET /api/produit
 *
 * 	- POST /api/produit
 *
 * 	- GET /api/produit/{id}
 *
 * 	- PUT /api/produit/{id}
 *
 * 	- DELETE /api/produit/{id}
 */
@RestController
@RequestMapping("/api")
public class ProduitController {
    private final ProduitService produitService;
    private final String files_produit ="templates/files-produits";
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/produit")
    public List<Produit> getProduits(){
        return produitService.getProduits();
    }
    @PostMapping("/produit")
    public Map<String ,String> createProduit(FormProduit produit, @RequestParam("file") MultipartFile multipartFile) throws ValidationException, IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String name= FileUploadUtil.saveFile(fileName, multipartFile, this.files_produit);
        produit.setImage(name);

        return produitService.createProduit(produit);
    }

    @PutMapping("/produit/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable(value="id") Long produitId, @RequestBody FormProduit produitDetails){
        Produit produit = produitService.updateProduit(produitId, produitDetails);
        return ResponseEntity.ok().body(produit);
    }
    @GetMapping("/produit/{id}")
    public Produit getProduit(@PathVariable(value="id") Long produitId){
        return produitService.getProduit(produitId);
    }
    @DeleteMapping("/produit/{id}")
    public Map<String, Boolean> deleteProduit(@PathVariable(value="id") Long produitId){
        return produitService.deleteProduit(produitId);
    }
}
