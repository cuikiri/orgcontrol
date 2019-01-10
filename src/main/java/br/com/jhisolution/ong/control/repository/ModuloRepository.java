package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Modulo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Modulo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

}
