package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.PessoaService;
import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing Pessoa.
 */
@Service
@Transactional
public class PessoaServiceImpl implements PessoaService {

    private final Logger log = LoggerFactory.getLogger(PessoaServiceImpl.class);

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    /**
     * Save a pessoa.
     *
     * @param pessoa the entity to save
     * @return the persisted entity
     */
    @Override
    public Pessoa save(Pessoa pessoa) {
        log.debug("Request to save Pessoa : {}", pessoa);
        return pessoaRepository.save(pessoa);
    }

    /**
     * Get all the pessoas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Pessoa> findAll(Pageable pageable) {
        log.debug("Request to get all Pessoas");
        return pessoaRepository.findAll(pageable);
    }

    /**
     * Get all the Pessoa with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<Pessoa> findAllWithEagerRelationships(Pageable pageable) {
        return pessoaRepository.findAllWithEagerRelationships(pageable);
    }
    


    /**
     *  get all the pessoas where Colaborador is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Pessoa> findAllWhereColaboradorIsNull() {
        log.debug("Request to get all pessoas where Colaborador is null");
        return StreamSupport
            .stream(pessoaRepository.findAll().spliterator(), false)
            .filter(pessoa -> pessoa.getColaborador() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the pessoas where Responsavel is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Pessoa> findAllWhereResponsavelIsNull() {
        log.debug("Request to get all pessoas where Responsavel is null");
        return StreamSupport
            .stream(pessoaRepository.findAll().spliterator(), false)
            .filter(pessoa -> pessoa.getResponsavel() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the pessoas where Aluno is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Pessoa> findAllWhereAlunoIsNull() {
        log.debug("Request to get all pessoas where Aluno is null");
        return StreamSupport
            .stream(pessoaRepository.findAll().spliterator(), false)
            .filter(pessoa -> pessoa.getAluno() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the pessoas where AlunoMae is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Pessoa> findAllWhereAlunoMaeIsNull() {
        log.debug("Request to get all pessoas where AlunoMae is null");
        return StreamSupport
            .stream(pessoaRepository.findAll().spliterator(), false)
            .filter(pessoa -> pessoa.getAlunoMae() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the pessoas where AlunoPai is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Pessoa> findAllWhereAlunoPaiIsNull() {
        log.debug("Request to get all pessoas where AlunoPai is null");
        return StreamSupport
            .stream(pessoaRepository.findAll().spliterator(), false)
            .filter(pessoa -> pessoa.getAlunoPai() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one pessoa by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Pessoa> findOne(Long id) {
        log.debug("Request to get Pessoa : {}", id);
        return pessoaRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the pessoa by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Pessoa : {}", id);
        pessoaRepository.deleteById(id);
    }
    
    @Transactional(readOnly = true)
	public Pessoa findOneByUser(Long id) {
    	log.debug("Request to get Pessoa by User: {}", id);
	    Pessoa pessoa = pessoaRepository.findOneByUser(id);
	    return pessoa;
	}
}
