package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Diario;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Diario entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiarioRepository extends JpaRepository<Diario, Long> {

}
