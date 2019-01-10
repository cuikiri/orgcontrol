package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoContratacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoContratacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoContratacaoRepository extends JpaRepository<TipoContratacao, Long> {

}
