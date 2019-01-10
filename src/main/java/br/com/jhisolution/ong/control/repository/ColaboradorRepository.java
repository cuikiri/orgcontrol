package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Colaborador;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Colaborador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

}
