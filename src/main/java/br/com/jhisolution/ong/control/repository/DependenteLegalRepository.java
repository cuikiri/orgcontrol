package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.DependenteLegal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DependenteLegal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DependenteLegalRepository extends JpaRepository<DependenteLegal, Long> {

}
