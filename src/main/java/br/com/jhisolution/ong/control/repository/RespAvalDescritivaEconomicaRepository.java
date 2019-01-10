package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespAvalDescritivaEconomica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespAvalDescritivaEconomica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespAvalDescritivaEconomicaRepository extends JpaRepository<RespAvalDescritivaEconomica, Long> {

}
