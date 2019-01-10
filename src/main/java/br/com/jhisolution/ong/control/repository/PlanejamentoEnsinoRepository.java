package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.PlanejamentoEnsino;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanejamentoEnsino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanejamentoEnsinoRepository extends JpaRepository<PlanejamentoEnsino, Long> {

}
