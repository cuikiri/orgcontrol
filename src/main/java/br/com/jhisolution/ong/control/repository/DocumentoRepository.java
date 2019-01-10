package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Documento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Documento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
