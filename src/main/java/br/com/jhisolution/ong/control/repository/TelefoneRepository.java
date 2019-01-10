package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Telefone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Telefone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
