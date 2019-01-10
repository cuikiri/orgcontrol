package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ItemAvaliacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemAvaliacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAvaliacaoRepository extends JpaRepository<ItemAvaliacao, Long> {

}
