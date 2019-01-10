package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativaEconomica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OpcaoRespAvalOptativaEconomica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpcaoRespAvalOptativaEconomicaRepository extends JpaRepository<OpcaoRespAvalOptativaEconomica, Long> {

}
