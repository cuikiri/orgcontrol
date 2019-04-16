package br.com.jhisolution.ong.control.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jhisolution.ong.control.domain.Endereco;
import br.com.jhisolution.ong.control.domain.Pessoa;


/**
 * Spring Data  repository for the Endereco entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	Page<Endereco> findAllByPessoa(Pageable pageable, Pessoa pessoa);
}
