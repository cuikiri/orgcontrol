package br.com.jhisolution.ong.control.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.jhisolution.ong.control.domain.Pessoa;
import br.com.jhisolution.ong.control.domain.Telefone;
import br.com.jhisolution.ong.control.repository.TelefoneRepository;
import br.com.jhisolution.ong.control.service.TelefoneService;

/**
 * Service Implementation for managing Telefone.
 */
@Service
@Transactional
public class TelefoneServiceImpl implements TelefoneService {

    private final Logger log = LoggerFactory.getLogger(TelefoneServiceImpl.class);

    private final TelefoneRepository telefoneRepository;

    public TelefoneServiceImpl(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }

    /**
     * Save a telefone.
     *
     * @param telefone the entity to save
     * @return the persisted entity
     */
    @Override
    public Telefone save(Telefone telefone) {
        log.debug("Request to save Telefone : {}", telefone);
        return telefoneRepository.save(telefone);
    }

    /**
     * Get all the telefones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Telefone> findAll(Pageable pageable) {
        log.debug("Request to get all Telefones");
        return telefoneRepository.findAll(pageable);
    }

    /**
     * Get all the telefones.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Telefone> findAllByPessoa(Pageable pageable, Pessoa pessoa) {
        log.debug("Request to get all Telefones");
        return telefoneRepository.findAllByPessoa(pageable, pessoa);
    }

    /**
     * Get one telefone by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Telefone> findOne(Long id) {
        log.debug("Request to get Telefone : {}", id);
        return telefoneRepository.findById(id);
    }

    /**
     * Delete the telefone by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Telefone : {}", id);
        telefoneRepository.deleteById(id);
    }
}
