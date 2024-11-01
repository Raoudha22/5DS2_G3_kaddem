package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.DetailEquipe;
import tn.esprit.spring.kaddem.repositories.DetailEquipeRepository;
import java.util.Optional;
@Slf4j
@AllArgsConstructor
@Service
public class DetailEquipeServiceImpl implements IDetailEquipeService {
    DetailEquipeRepository detailEquipeRepository;

    @Override
    public DetailEquipe addDetailEquipe(DetailEquipe detailEquipe) {
        return detailEquipeRepository.save(detailEquipe);
    }

    @Override
    public DetailEquipe retrieveDetailEquipe(Integer id) {
        Optional<DetailEquipe> detailEquipe = detailEquipeRepository.findById(id);
        return detailEquipe.orElse(null);
    }

    @Override
    public DetailEquipe updateDetailEquipe(DetailEquipe detailEquipe) {
        if (detailEquipeRepository.existsById(detailEquipe.getIdDetailEquipe())) {
            return detailEquipeRepository.save(detailEquipe);
        }
        return null;
    }

    @Override
    public void removeDetailEquipe(Integer id) {
        Optional<DetailEquipe> detailEquipe = detailEquipeRepository.findById(id);
        detailEquipe.ifPresent(detailEquipeRepository::delete);
    }
}
