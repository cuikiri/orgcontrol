package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespAvalOptativaEconomica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespAvalOptativaEconomica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespAvalOptativaEconomicaRepository extends JpaRepository<RespAvalOptativaEconomica, Long> {

}
