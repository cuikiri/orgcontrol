package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Doenca;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Doenca entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoencaRepository extends JpaRepository<Doenca, Long> {

}
