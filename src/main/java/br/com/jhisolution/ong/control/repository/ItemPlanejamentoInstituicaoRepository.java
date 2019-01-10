package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoInstituicao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemPlanejamentoInstituicao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemPlanejamentoInstituicaoRepository extends JpaRepository<ItemPlanejamentoInstituicao, Long> {

}
