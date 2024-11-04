package tn.esprit.spring.kaddem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.spring.kaddem.entities.Universite;
import java.util.Date; // Import for Date class
import java.util.List; // Import for List interface

@Repository
public interface UniversiteRepository extends CrudRepository<Universite,Integer> {
  List<Universite> findUniversitesByDateRange(Date startDate, Date endDate);



}
