package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Materia;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Materia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {

}
