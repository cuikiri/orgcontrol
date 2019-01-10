package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoBloco;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoBloco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoBlocoRepository extends JpaRepository<TipoBloco, Long> {

}
