package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.PlanejamentoInstituicao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PlanejamentoInstituicao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanejamentoInstituicaoRepository extends JpaRepository<PlanejamentoInstituicao, Long> {

}
