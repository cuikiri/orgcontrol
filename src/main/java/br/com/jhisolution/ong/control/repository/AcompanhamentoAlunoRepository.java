package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.AcompanhamentoAluno;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcompanhamentoAluno entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcompanhamentoAlunoRepository extends JpaRepository<AcompanhamentoAluno, Long> {

}
