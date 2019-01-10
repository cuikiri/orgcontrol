package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.TipoCurso;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TipoCurso entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoCursoRepository extends JpaRepository<TipoCurso, Long> {

}
