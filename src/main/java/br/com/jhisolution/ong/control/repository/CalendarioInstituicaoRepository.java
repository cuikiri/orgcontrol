package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.CalendarioInstituicao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CalendarioInstituicao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CalendarioInstituicaoRepository extends JpaRepository<CalendarioInstituicao, Long> {

}
