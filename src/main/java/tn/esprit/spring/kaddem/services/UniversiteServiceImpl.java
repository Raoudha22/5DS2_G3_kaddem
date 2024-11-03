package tn.esprit.spring.kaddem.services;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
@Slf4j
@Service
public class UniversiteServiceImpl implements IUniversiteService {

    private static final Logger logger = (Logger) LogManager.getLogger(UniversiteServiceImpl.class);


    @Autowired
    UniversiteRepository universiteRepository;

    @Autowired
    DepartementRepository departementRepository;

    public UniversiteServiceImpl() {
        // Constructor
    }

    public List<Universite> retrieveAllUniversites() {
        log.info("Retrieving all universities");
        List<Universite> universites = (List<Universite>) universiteRepository.findAll();
        log.debug("Found {} universities", universites.size());
        return universites;
    }

    public Universite addUniversite(Universite u) {
        log.info("Adding university: {}", u);
        Universite savedUniversite = universiteRepository.save(u);
        log.debug("Added university with ID: {}", savedUniversite.getIdUniv());
        return savedUniversite;
    }

    public Universite updateUniversite(Universite u) {
        log.info("Updating university with ID: {}", u.getIdUniv());
        Universite updatedUniversite = universiteRepository.save(u);
        log.debug("Updated university with ID: {}", updatedUniversite.getIdUniv());
        return updatedUniversite;
    }

    public Universite retrieveUniversite(Integer idUniversite) {
        log.info("Retrieving university with ID: {}", idUniversite);
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        if (u != null) {
            log.debug("Found university: {}", u);
        } else {
            log.warn("University with ID {} not found", idUniversite);
        }
        return u;
    }

    public void deleteUniversite(Integer idUniversite) {
        log.info("Deleting university with ID: {}", idUniversite);
        Universite u = retrieveUniversite(idUniversite);
        if (u != null) {
            universiteRepository.delete(u);
            log.debug("Deleted university with ID: {}", idUniversite);
        } else {
            log.warn("University with ID {} not found, cannot delete", idUniversite);
        }
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement) {
        log.info("Assigning department with ID {} to university with ID {}", idDepartement, idUniversite);
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        Departement d = departementRepository.findById(idDepartement).orElse(null);

        if (u != null && d != null) {
            u.getDepartements().add(d);
            universiteRepository.save(u);
            log.debug("Assigned department with ID {} to university with ID {}", idDepartement, idUniversite);
        } else {
            if (u == null) {
                log.warn("University with ID {} not found", idUniversite);
            }
            if (d == null) {
                log.warn("Department with ID {} not found", idDepartement);
            }
        }
    }

    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        log.info("Retrieving departments for university with ID {}", idUniversite);
        Universite u = universiteRepository.findById(idUniversite).orElse(null);

        if (u != null) {
            Set<Departement> departements = u.getDepartements();
            log.debug("Found {} departments for university with ID {}", departements.size(), idUniversite);
            return departements;
        } else {
            log.warn("University with ID {} not found", idUniversite);
            return null;
        }
    }
}
