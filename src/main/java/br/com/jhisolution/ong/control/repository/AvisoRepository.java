package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Aviso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Aviso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {

}
