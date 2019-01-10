package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoConceito;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoConceito entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoConceitoRepository extends JpaRepository<TipoConceito, Long> {

}
