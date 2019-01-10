package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoBiotipo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoBiotipo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoBiotipoRepository extends JpaRepository<TipoBiotipo, Long> {

}
