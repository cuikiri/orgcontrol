package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Religiao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Religiao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReligiaoRepository extends JpaRepository<Religiao, Long> {

}
