package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.EstadoCivil;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EstadoCivil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Long> {

}
