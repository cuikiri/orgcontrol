package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RespostaAvaliacaoService;
import br.com.jhisolution.ong.control.domain.RespostaAvaliacao;
import br.com.jhisolution.ong.control.repository.RespostaAvaliacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RespostaAvaliacao.
 */
@Service
@Transactional
public class RespostaAvaliacaoServiceImpl implements RespostaAvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(RespostaAvaliacaoServiceImpl.class);

    private final RespostaAvaliacaoRepository respostaAvaliacaoRepository;

    public RespostaAvaliacaoServiceImpl(RespostaAvaliacaoRepository respostaAvaliacaoRepository) {
        this.respostaAvaliacaoRepository = respostaAvaliacaoRepository;
    }

    /**
     * Save a respostaAvaliacao.
     *
     * @param respostaAvaliacao the entity to save
     * @return the persisted entity
     */
    @Override
    public RespostaAvaliacao save(RespostaAvaliacao respostaAvaliacao) {
        log.debug("Request to save RespostaAvaliacao : {}", respostaAvaliacao);
        return respostaAvaliacaoRepository.save(respostaAvaliacao);
    }

    /**
     * Get all the respostaAvaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RespostaAvaliacao> findAll(Pageable pageable) {
        log.debug("Request to get all RespostaAvaliacaos");
        return respostaAvaliacaoRepository.findAll(pageable);
    }


    /**
     * Get one respostaAvaliacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespostaAvaliacao> findOne(Long id) {
        log.debug("Request to get RespostaAvaliacao : {}", id);
        return respostaAvaliacaoRepository.findById(id);
    }

    /**
     * Delete the respostaAvaliacao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespostaAvaliacao : {}", id);
        respostaAvaliacaoRepository.deleteById(id);
    }
}
