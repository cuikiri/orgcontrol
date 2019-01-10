package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ConteudoProgramatico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConteudoProgramatico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConteudoProgramaticoRepository extends JpaRepository<ConteudoProgramatico, Long> {

}
