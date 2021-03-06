package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemPlanejamentoAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemPlanejamentoAtividadeRepository extends JpaRepository<ItemPlanejamentoAtividade, Long> {

}
