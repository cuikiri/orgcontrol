package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Instituicao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Instituicao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {

}
