package tn.esprit.spring.kaddem.dtos;

import lombok.Data;
import tn.esprit.spring.kaddem.entities.Niveau;

@Data
public class EquipeDTO {
    private Integer idEquipe;
    private String nomEquipe;
    private Niveau niveau;
}
