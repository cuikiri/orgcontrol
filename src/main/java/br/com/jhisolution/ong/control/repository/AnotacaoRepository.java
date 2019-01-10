package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Anotacao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Anotacao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {

}
