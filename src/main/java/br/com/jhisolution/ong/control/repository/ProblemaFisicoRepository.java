package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ProblemaFisico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProblemaFisico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProblemaFisicoRepository extends JpaRepository<ProblemaFisico, Long> {

}
