package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.AgendaColaborador;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AgendaColaborador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgendaColaboradorRepository extends JpaRepository<AgendaColaborador, Long> {

}
