package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespAtivDescritiva;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespAtivDescritiva entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespAtivDescritivaRepository extends JpaRepository<RespAtivDescritiva, Long> {

}
