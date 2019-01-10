package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoAtividadeRepository extends JpaRepository<TipoAtividade, Long> {

}
