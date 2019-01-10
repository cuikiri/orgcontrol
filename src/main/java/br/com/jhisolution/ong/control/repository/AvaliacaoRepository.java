package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Avaliacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Avaliacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}
