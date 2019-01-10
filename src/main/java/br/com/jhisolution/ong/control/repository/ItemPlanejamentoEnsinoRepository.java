package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ItemPlanejamentoEnsino;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemPlanejamentoEnsino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemPlanejamentoEnsinoRepository extends JpaRepository<ItemPlanejamentoEnsino, Long> {

}
