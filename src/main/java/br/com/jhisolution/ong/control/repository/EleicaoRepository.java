package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Eleicao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Eleicao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EleicaoRepository extends JpaRepository<Eleicao, Long> {

}
