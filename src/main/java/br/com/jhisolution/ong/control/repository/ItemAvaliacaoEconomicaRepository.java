package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ItemAvaliacaoEconomica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ItemAvaliacaoEconomica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ItemAvaliacaoEconomicaRepository extends JpaRepository<ItemAvaliacaoEconomica, Long> {

}
