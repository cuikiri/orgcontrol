package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Biotipo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Biotipo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BiotipoRepository extends JpaRepository<Biotipo, Long> {

}
