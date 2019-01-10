package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Vacina;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Vacina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VacinaRepository extends JpaRepository<Vacina, Long> {

}
