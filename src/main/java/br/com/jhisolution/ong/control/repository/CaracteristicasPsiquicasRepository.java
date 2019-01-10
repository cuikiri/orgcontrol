package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.CaracteristicasPsiquicas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CaracteristicasPsiquicas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CaracteristicasPsiquicasRepository extends JpaRepository<CaracteristicasPsiquicas, Long> {

}
