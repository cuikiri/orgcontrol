package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Generalidade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Generalidade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeneralidadeRepository extends JpaRepository<Generalidade, Long> {

}
