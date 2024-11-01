package tn.esprit.spring.kaddem.entities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class DetailEquipe implements Serializable {

    private static final Logger logger = LogManager.getLogger(DetailEquipe.class);

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;

    @OneToOne(mappedBy="detailEquipe")
    private Equipe equipe;

    public DetailEquipe() {
        logger.info("DetailEquipe default constructor called");
    }

    public DetailEquipe(Integer salle, String thematique) {
        super();
        this.salle = salle;
        this.thematique = thematique;
        logger.debug("DetailEquipe constructor with salle: {}, thematique: {} called", salle, thematique);
    }

    public DetailEquipe(Integer idDetailEquipe, Integer salle, String thematique) {
        super();
        this.idDetailEquipe = idDetailEquipe;
        this.salle = salle;
        this.thematique = thematique;
        logger.debug("DetailEquipe constructor with idDetailEquipe: {}, salle: {}, thematique: {} called", idDetailEquipe, salle, thematique);
    }

    public Equipe getEquipe() {
        logger.info("getEquipe called");
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        logger.info("setEquipe called with equipe: {}", equipe);
        this.equipe = equipe;
    }

    public Integer getIdDetailEquipe() {
        logger.info("getIdDetailEquipe called");
        return idDetailEquipe;
    }

    public void setIdDetailEquipe(Integer idDetailEquipe) {
        logger.info("setIdDetailEquipe called with idDetailEquipe: {}", idDetailEquipe);
        this.idDetailEquipe = idDetailEquipe;
    }

    public Integer getSalle() {
        logger.info("getSalle called");
        return salle;
    }

    public void setSalle(Integer salle) {
        logger.debug("setSalle called with salle: {}", salle);
        this.salle = salle;
    }

    public String getThematique() {
        logger.info("getThematique called");
        return thematique;
    }

    public void setThematique(String thematique) {
        logger.debug("setThematique called with thematique: {}", thematique);
        this.thematique = thematique;
    }
}
