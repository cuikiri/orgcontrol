package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.PeriodoSemana;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PeriodoSemana entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeriodoSemanaRepository extends JpaRepository<PeriodoSemana, Long> {

}
