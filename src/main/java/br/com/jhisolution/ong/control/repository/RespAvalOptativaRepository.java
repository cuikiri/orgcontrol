package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespAvalOptativa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespAvalOptativa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespAvalOptativaRepository extends JpaRepository<RespAvalOptativa, Long> {

}
