package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ItemAcompanhamentoAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemAcompanhamentoAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAcompanhamentoAtividadeRepository extends JpaRepository<ItemAcompanhamentoAtividade, Long> {

}
