package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.DiaNaoUtil;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DiaNaoUtil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiaNaoUtilRepository extends JpaRepository<DiaNaoUtil, Long> {

}
