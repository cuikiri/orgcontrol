package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Advertencia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Advertencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdvertenciaRepository extends JpaRepository<Advertencia, Long> {

}
