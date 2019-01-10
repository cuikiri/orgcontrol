package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoAvaliacaoService;
import br.com.jhisolution.ong.control.domain.TipoAvaliacao;
import br.com.jhisolution.ong.control.repository.TipoAvaliacaoRepository;
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
 * Service Implementation for managing TipoAvaliacao.
 */
@Service
@Transactional
public class TipoAvaliacaoServiceImpl implements TipoAvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(TipoAvaliacaoServiceImpl.class);

    private final TipoAvaliacaoRepository tipoAvaliacaoRepository;

    public TipoAvaliacaoServiceImpl(TipoAvaliacaoRepository tipoAvaliacaoRepository) {
        this.tipoAvaliacaoRepository = tipoAvaliacaoRepository;
    }

    /**
     * Save a tipoAvaliacao.
     *
     * @param tipoAvaliacao the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoAvaliacao save(TipoAvaliacao tipoAvaliacao) {
        log.debug("Request to save TipoAvaliacao : {}", tipoAvaliacao);
        return tipoAvaliacaoRepository.save(tipoAvaliacao);
    }

    /**
     * Get all the tipoAvaliacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoAvaliacao> findAll(Pageable pageable) {
        log.debug("Request to get all TipoAvaliacaos");
        return tipoAvaliacaoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoAvaliacaos where Avaliacao is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoAvaliacao> findAllWhereAvaliacaoIsNull() {
        log.debug("Request to get all tipoAvaliacaos where Avaliacao is null");
        return StreamSupport
            .stream(tipoAvaliacaoRepository.findAll().spliterator(), false)
            .filter(tipoAvaliacao -> tipoAvaliacao.getAvaliacao() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoAvaliacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoAvaliacao> findOne(Long id) {
        log.debug("Request to get TipoAvaliacao : {}", id);
        return tipoAvaliacaoRepository.findById(id);
    }

    /**
     * Delete the tipoAvaliacao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoAvaliacao : {}", id);
        tipoAvaliacaoRepository.deleteById(id);
    }
}
