package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.RespAtivOptativa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespAtivOptativa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespAtivOptativaRepository extends JpaRepository<RespAtivOptativa, Long> {

}
