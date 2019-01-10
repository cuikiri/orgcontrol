package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.PeriodoAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PeriodoAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeriodoAtividadeRepository extends JpaRepository<PeriodoAtividade, Long> {

}
