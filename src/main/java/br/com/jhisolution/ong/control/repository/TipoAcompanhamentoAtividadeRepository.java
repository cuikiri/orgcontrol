package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoAcompanhamentoAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoAcompanhamentoAtividadeRepository extends JpaRepository<TipoAcompanhamentoAtividade, Long> {

}
