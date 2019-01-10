package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoAvaliacaoEconomica;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoAvaliacaoEconomica entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoAvaliacaoEconomicaRepository extends JpaRepository<TipoAvaliacaoEconomica, Long> {

}
