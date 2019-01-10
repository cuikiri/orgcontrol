package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.FotoAcompanhamentoAluno;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FotoAcompanhamentoAluno entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FotoAcompanhamentoAlunoRepository extends JpaRepository<FotoAcompanhamentoAluno, Long> {

}
