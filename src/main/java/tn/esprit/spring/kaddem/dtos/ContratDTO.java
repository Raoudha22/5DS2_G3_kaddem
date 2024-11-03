package tn.esprit.spring.kaddem.dtos;

import lombok.Data;
import tn.esprit.spring.kaddem.entities.Specialite;

import java.util.Date;

@Data
public class ContratDTO {
    private Integer idContrat;
    private Date dateDebutContrat;
    private Date dateFinContrat;
    private Specialite specialite;
    private Boolean archive;
    private Integer montantContrat;
}
