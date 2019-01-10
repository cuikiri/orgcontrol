package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.OpcaoRespAtividade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OpcaoRespAtividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpcaoRespAtividadeRepository extends JpaRepository<OpcaoRespAtividade, Long> {

}
