package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.DetailEquipe;
import java.util.Optional;


public interface IDetailEquipeService {

    // Method to add a DetailEquipe
    DetailEquipe addDetailEquipe(DetailEquipe detailEquipe);

    // Method to retrieve a DetailEquipe by ID
    Optional<DetailEquipe> getDetailEquipeById(Integer id);

    // Method to update a DetailEquipe
    DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe);

    // Method to delete a DetailEquipe by ID
    void deleteDetailEquipe(Integer id);
}
