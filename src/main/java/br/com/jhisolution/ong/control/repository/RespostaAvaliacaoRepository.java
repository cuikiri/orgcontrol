package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespostaAvaliacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespostaAvaliacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespostaAvaliacaoRepository extends JpaRepository<RespostaAvaliacao, Long> {

}
