package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespAvalDescritiva;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespAvalDescritiva entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespAvalDescritivaRepository extends JpaRepository<RespAvalDescritiva, Long> {

}
