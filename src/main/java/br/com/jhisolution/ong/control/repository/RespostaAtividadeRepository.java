package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespostaAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespostaAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespostaAtividadeRepository extends JpaRepository<RespostaAtividade, Long> {

}
