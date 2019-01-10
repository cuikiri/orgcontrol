package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Ensino;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ensino entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnsinoRepository extends JpaRepository<Ensino, Long> {

}
