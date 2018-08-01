package jwd.wafepa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Automobil;


@Repository
public interface AutomobilRepository extends PagingAndSortingRepository<Automobil, Long>{

	Page<Automobil>findByKompanijaId(Long autoId,Pageable pageRequest);
	
	
	@Query("SELECT a FROM Automobil a WHERE "
			+ "(:model IS NULL or a.model like :model ) AND "
			+ "(:godiste IS NULL OR a.godiste >=  :godiste  ) AND "
			+ "(:dajId IS NULL OR a.kompanija.id = :dajId) AND "
			+ "(:potrosnja IS NULL OR a.potrosnja <= :potrosnja)")
	Page<Automobil> pretraga(
			@Param("model") String model, 
			@Param("godiste") Integer godiste, 
			@Param("dajId") Long Dajid,
			@Param("potrosnja") Double potrosnja,
			Pageable pageRequest);
	
	
}
