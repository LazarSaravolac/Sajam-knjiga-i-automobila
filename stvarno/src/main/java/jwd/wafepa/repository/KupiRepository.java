package jwd.wafepa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Kupi;

@Repository
public interface KupiRepository extends JpaRepository<Kupi, Long>{

}
