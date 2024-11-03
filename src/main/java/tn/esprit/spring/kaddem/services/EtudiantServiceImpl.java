package tn.esprit.spring.kaddem.services;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantServiceImpl implements IEtudiantService{
	private static final Logger logger = LoggerFactory.getLogger(EtudiantServiceImpl.class);

	@Autowired
	EtudiantRepository etudiantRepository ;
	@Autowired
	ContratRepository contratRepository;
	@Autowired
	EquipeRepository equipeRepository;
    @Autowired
    DepartementRepository departementRepository;

	public List<Etudiant> retrieveAllEtudiants(){
	return (List<Etudiant>) etudiantRepository.findAll();
	}

	public Etudiant addEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant updateEtudiant (Etudiant e){
		return etudiantRepository.save(e);
	}

	public Etudiant retrieveEtudiant(Integer  idEtudiant){
		Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);

		if (optionalEtudiant.isPresent()) {
			return optionalEtudiant.get();
		} else {
			// Handle the case where the Etudiant is not found
			throw new EntityNotFoundException("Etudiant with ID " + idEtudiant + " not found.");
		}
	}

	public void removeEtudiant(Integer idEtudiant){
	var e=retrieveEtudiant(idEtudiant);
	etudiantRepository.delete(e);
	}

	public void assignEtudiantToDepartement (Integer etudiantId, Integer departementId){
        var etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        var departement = departementRepository.findById(departementId).orElse(null);

		if (etudiant == null) {
			logger.warn("Etudiant not found with ID: {}", etudiantId);
			return;
		}

		if (departement == null) {
			logger.warn("Departement not found with ID: {}", departementId);
			return;
		}

        etudiant.setDepartement(departement);
        etudiantRepository.save(etudiant);
	}
	@Transactional
	public Etudiant addAndAssignEtudiantToEquipeAndContract(Etudiant e, Integer idContrat, Integer idEquipe){
		Contrat c=contratRepository.findById(idContrat).orElse(null);
		Equipe eq=equipeRepository.findById(idEquipe).orElse(null);

		if (c == null) {
			logger.warn("Contrat not found with ID: {}", idContrat);
			throw new IllegalArgumentException("Contrat not found for ID: " + idContrat);
		}

		if (eq == null) {
			logger.warn("Equipe not found with ID: {}", idEquipe);
			throw new IllegalArgumentException("Equipe not found for ID: " + idEquipe);
		}

		c.setEtudiant(e);
		eq.getEtudiants().add(e);
return e;
	}

	public 	List<Etudiant> getEtudiantsByDepartement (Integer idDepartement){
return  etudiantRepository.findEtudiantsByDepartementIdDepart((idDepartement));
	}
}
