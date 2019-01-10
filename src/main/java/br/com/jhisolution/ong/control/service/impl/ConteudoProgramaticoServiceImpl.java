package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.ConteudoProgramaticoService;
import br.com.jhisolution.ong.control.domain.ConteudoProgramatico;
import br.com.jhisolution.ong.control.repository.ConteudoProgramaticoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ConteudoProgramatico.
 */
@Service
@Transactional
public class ConteudoProgramaticoServiceImpl implements ConteudoProgramaticoService {

    private final Logger log = LoggerFactory.getLogger(ConteudoProgramaticoServiceImpl.class);

    private final ConteudoProgramaticoRepository conteudoProgramaticoRepository;

    public ConteudoProgramaticoServiceImpl(ConteudoProgramaticoRepository conteudoProgramaticoRepository) {
        this.conteudoProgramaticoRepository = conteudoProgramaticoRepository;
    }

    /**
     * Save a conteudoProgramatico.
     *
     * @param conteudoProgramatico the entity to save
     * @return the persisted entity
     */
    @Override
    public ConteudoProgramatico save(ConteudoProgramatico conteudoProgramatico) {
        log.debug("Request to save ConteudoProgramatico : {}", conteudoProgramatico);
        return conteudoProgramaticoRepository.save(conteudoProgramatico);
    }

    /**
     * Get all the conteudoProgramaticos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ConteudoProgramatico> findAll(Pageable pageable) {
        log.debug("Request to get all ConteudoProgramaticos");
        return conteudoProgramaticoRepository.findAll(pageable);
    }


    /**
     * Get one conteudoProgramatico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ConteudoProgramatico> findOne(Long id) {
        log.debug("Request to get ConteudoProgramatico : {}", id);
        return conteudoProgramaticoRepository.findById(id);
    }

    /**
     * Delete the conteudoProgramatico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ConteudoProgramatico : {}", id);
        conteudoProgramaticoRepository.deleteById(id);
    }
}
