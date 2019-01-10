package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.AvaliacaoService;
import br.com.jhisolution.ong.control.domain.Avaliacao;
import br.com.jhisolution.ong.control.repository.AvaliacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Avaliacao.
 */
@Service
@Transactional
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoServiceImpl.class);

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoServiceImpl(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    /**
     * Save a avaliacao.
     *
     * @param avaliacao the entity to save
     * @return the persisted entity
     */
    @Override
    public Avaliacao save(Avaliacao avaliacao) {
        log.debug("Request to save Avaliacao : {}", avaliacao);
        return avaliacaoRepository.save(avaliacao);
    }

    /**
     * Get all the avaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Avaliacao> findAll(Pageable pageable) {
        log.debug("Request to get all Avaliacaos");
        return avaliacaoRepository.findAll(pageable);
    }


    /**
     * Get one avaliacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Avaliacao> findOne(Long id) {
        log.debug("Request to get Avaliacao : {}", id);
        return avaliacaoRepository.findById(id);
    }

    /**
     * Delete the avaliacao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Avaliacao : {}", id);
        avaliacaoRepository.deleteById(id);
    }
}
