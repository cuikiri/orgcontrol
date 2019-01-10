package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.AvaliacaoEconomica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AvaliacaoEconomica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvaliacaoEconomicaRepository extends JpaRepository<AvaliacaoEconomica, Long> {

}
