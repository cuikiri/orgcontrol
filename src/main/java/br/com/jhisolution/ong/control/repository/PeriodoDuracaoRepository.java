package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.PeriodoDuracao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PeriodoDuracao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeriodoDuracaoRepository extends JpaRepository<PeriodoDuracao, Long> {

}
