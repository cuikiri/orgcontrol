package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ObservacaoProfessor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ObservacaoProfessor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObservacaoProfessorRepository extends JpaRepository<ObservacaoProfessor, Long> {

}
