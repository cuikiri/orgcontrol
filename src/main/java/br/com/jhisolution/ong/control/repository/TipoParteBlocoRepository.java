package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoParteBloco;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoParteBloco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoParteBlocoRepository extends JpaRepository<TipoParteBloco, Long> {

}
