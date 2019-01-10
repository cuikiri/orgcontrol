package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoUnidade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoUnidade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoUnidadeRepository extends JpaRepository<TipoUnidade, Long> {

}
