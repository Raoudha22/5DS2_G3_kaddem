package tn.esprit.spring.kaddem.services;

import tn.esprit.spring.kaddem.entities.DetailEquipe;

public interface IDetailEquipeService {
    DetailEquipe addDetailEquipe(DetailEquipe detailEquipe);
    DetailEquipe retrieveDetailEquipe(Integer id);
    DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe);
    void removeDetailEquipe(Integer id);
}
