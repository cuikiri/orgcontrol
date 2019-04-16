package br.com.jhisolution.ong.control.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhisolution.ong.control.domain.Endereco;
import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.repository.EnderecoRepository;
import br.com.jhisolution.ong.control.service.EnderecoService;

/**
 * Service Implementation for managing Endereco.
 */
@Service
@Transactional
public class EnderecoServiceImpl implements EnderecoService {

    private final Logger log = LoggerFactory.getLogger(EnderecoServiceImpl.class);

    private final EnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    /**
     * Save a endereco.
     *
     * @param endereco the entity to save
     * @return the persisted entity
     */
    @Override
    public Endereco save(Endereco endereco) {
        log.debug("Request to save Endereco : {}", endereco);
        return enderecoRepository.save(endereco);
    }

    /**
     * Get all the enderecos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Endereco> findAll(Pageable pageable) {
        log.debug("Request to get all Enderecos");
        return enderecoRepository.findAll(pageable);
    }
    
    /**
     * Get all the enderecos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Endereco> findAllByPessoa(Pageable pageable, Pessoa pessoa) {
        log.debug("Request to get all Enderecos");
        return enderecoRepository.findAllByPessoa(pageable, pessoa);
    }

    /**
     *  get all the enderecos where Unidade is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Endereco> findAllWhereUnidadeIsNull() {
        log.debug("Request to get all enderecos where Unidade is null");
        return StreamSupport
            .stream(enderecoRepository.findAll().spliterator(), false)
            .filter(endereco -> endereco.getUnidade() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one endereco by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Endereco> findOne(Long id) {
        log.debug("Request to get Endereco : {}", id);
        return enderecoRepository.findById(id);
    }

    /**
     * Delete the endereco by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Endereco : {}", id);
        enderecoRepository.deleteById(id);
    }
}
