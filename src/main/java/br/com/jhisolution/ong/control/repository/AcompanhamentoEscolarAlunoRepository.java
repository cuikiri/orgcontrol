package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.AcompanhamentoEscolarAluno;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcompanhamentoEscolarAluno entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcompanhamentoEscolarAlunoRepository extends JpaRepository<AcompanhamentoEscolarAluno, Long> {

}
