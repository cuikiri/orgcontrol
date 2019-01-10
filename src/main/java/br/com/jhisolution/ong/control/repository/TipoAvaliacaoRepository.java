package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoAvaliacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoAvaliacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoAvaliacaoRepository extends JpaRepository<TipoAvaliacao, Long> {

}
