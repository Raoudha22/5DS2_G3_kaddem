package tn.esprit.spring.kaddem.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService{
    private static final Logger logger = LoggerFactory.getLogger(UniversiteServiceImpl.class);
    // Define constants for reuse
    private static final String UNIVERSITE_NOT_FOUND = "Universite not found with id: ";


    UniversiteRepository universiteRepository;

    DepartementRepository departementRepository;
    public UniversiteServiceImpl() {
        logger.info("UniversiteServiceImpl instance created.");
    }

  public   List<Universite> retrieveAllUniversites(){
return (List<Universite>) universiteRepository.findAll();
    }

 public    Universite addUniversite (Universite  u){
return  (universiteRepository.save(u));
    }

 public    Universite updateUniversite (Universite  u){
     return  (universiteRepository.save(u));
    }

  public Universite retrieveUniversite (Integer idUniversite){
      return universiteRepository.findById(idUniversite)
              .orElseThrow(() -> new EntityNotFoundException(UNIVERSITE_NOT_FOUND + idUniversite));
    }
    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement){
        Universite u= universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new EntityNotFoundException(UNIVERSITE_NOT_FOUND + idUniversite));
        Departement d= departementRepository.findById(idDepartement)
                .orElseThrow(() -> new EntityNotFoundException(UNIVERSITE_NOT_FOUND + idDepartement));

        u.getDepartements().add(d);
        universiteRepository.save(u);
    }

    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite){
        Universite u=universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new EntityNotFoundException(UNIVERSITE_NOT_FOUND + idUniversite));

        return u.getDepartements();
    }
}
