package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Chapa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Chapa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChapaRepository extends JpaRepository<Chapa, Long> {

}
