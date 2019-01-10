package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespostaAvaliacaoEconomica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespostaAvaliacaoEconomica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespostaAvaliacaoEconomicaRepository extends JpaRepository<RespostaAvaliacaoEconomica, Long> {

}
