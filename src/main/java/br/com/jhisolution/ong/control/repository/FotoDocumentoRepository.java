package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.FotoDocumento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FotoDocumento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FotoDocumentoRepository extends JpaRepository<FotoDocumento, Long> {

}
