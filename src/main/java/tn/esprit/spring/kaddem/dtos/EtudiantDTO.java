package tn.esprit.spring.kaddem.dtos;

import lombok.Data;
import tn.esprit.spring.kaddem.entities.Option;

@Data
public class EtudiantDTO {
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;
    private Option op;
}
