package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ConceitoAcompanhamento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConceitoAcompanhamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConceitoAcompanhamentoRepository extends JpaRepository<ConceitoAcompanhamento, Long> {

}
