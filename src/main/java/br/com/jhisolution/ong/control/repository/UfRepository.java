package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Uf;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Uf entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

}
