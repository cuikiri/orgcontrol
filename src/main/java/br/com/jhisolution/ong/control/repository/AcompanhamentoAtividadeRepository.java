package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.AcompanhamentoAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcompanhamentoAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcompanhamentoAtividadeRepository extends JpaRepository<AcompanhamentoAtividade, Long> {

}
