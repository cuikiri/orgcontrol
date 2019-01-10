package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoadoBiologico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoadoBiologico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoadoBiologicoRepository extends JpaRepository<TipoadoBiologico, Long> {

}
