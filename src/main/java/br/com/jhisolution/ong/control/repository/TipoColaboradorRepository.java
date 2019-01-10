package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoColaborador;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoColaborador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoColaboradorRepository extends JpaRepository<TipoColaborador, Long> {

}
