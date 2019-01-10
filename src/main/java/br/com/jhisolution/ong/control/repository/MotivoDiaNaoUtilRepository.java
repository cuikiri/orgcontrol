package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.MotivoDiaNaoUtil;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MotivoDiaNaoUtil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MotivoDiaNaoUtilRepository extends JpaRepository<MotivoDiaNaoUtil, Long> {

}
