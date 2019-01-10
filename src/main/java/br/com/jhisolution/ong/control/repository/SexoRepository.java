package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Sexo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sexo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SexoRepository extends JpaRepository<Sexo, Long> {

}
