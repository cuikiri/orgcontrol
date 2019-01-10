package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.HorarioMateria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HorarioMateria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HorarioMateriaRepository extends JpaRepository<HorarioMateria, Long> {

}
