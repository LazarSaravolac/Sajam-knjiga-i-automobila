package jwd.wafepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Iznajmi;

@Repository
public interface IznajmiRepository extends JpaRepository<Iznajmi, Long> {

}
