package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.OpcaoRespAvalOptativa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OpcaoRespAvalOptativa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OpcaoRespAvalOptativaRepository extends JpaRepository<OpcaoRespAvalOptativa, Long> {

}
