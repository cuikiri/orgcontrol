package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.BimestreAcompanhamento;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BimestreAcompanhamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BimestreAcompanhamentoRepository extends JpaRepository<BimestreAcompanhamento, Long> {

}
