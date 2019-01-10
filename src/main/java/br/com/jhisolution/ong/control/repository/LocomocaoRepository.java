package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Locomocao;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Locomocao entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocomocaoRepository extends JpaRepository<Locomocao, Long> {

}
