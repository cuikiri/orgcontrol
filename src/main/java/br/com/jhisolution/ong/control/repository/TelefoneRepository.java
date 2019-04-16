package br.com.jhisolution.ong.control.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.domain.Telefone;


/**
 * Spring Data  repository for the Telefone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	Page<Telefone> findAllByPessoa(Pageable pageable, Pessoa pessoa);
}
