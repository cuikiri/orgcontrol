package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Raca;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Raca entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RacaRepository extends JpaRepository<Raca, Long> {

}
