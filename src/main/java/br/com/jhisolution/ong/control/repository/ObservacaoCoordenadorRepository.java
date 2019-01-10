package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.ObservacaoCoordenador;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ObservacaoCoordenador entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObservacaoCoordenadorRepository extends JpaRepository<ObservacaoCoordenador, Long> {

}
