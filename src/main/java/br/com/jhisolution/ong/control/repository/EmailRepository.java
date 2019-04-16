package br.com.jhisolution.ong.control.repository;

import br.com.jhisolution.ong.control.domain.Email;
import br.com.jhisolution.ong.control.domain.Endereco;
import br.com.jhisolution.ong.control.domain.Pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Email entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
	Page<Email> findAllByPessoa(Pageable pageable, Pessoa pessoa);
}
