package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.PlanejamentoAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanejamentoAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanejamentoAtividadeRepository extends JpaRepository<PlanejamentoAtividade, Long> {

}
