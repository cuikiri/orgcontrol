package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoDocumento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoDocumento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

}
