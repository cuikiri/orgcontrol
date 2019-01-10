package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoAcompanhamentoAluno;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoAcompanhamentoAluno entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoAcompanhamentoAlunoRepository extends JpaRepository<TipoAcompanhamentoAluno, Long> {

}
