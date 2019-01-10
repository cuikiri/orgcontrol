package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.TipoContratacaoService;
import br.com.jhisolution.ong.control.domain.TipoContratacao;
import br.com.jhisolution.ong.control.repository.TipoContratacaoRepository;
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
 * Service Implementation for managing TipoContratacao.
 */
@Service
@Transactional
public class TipoContratacaoServiceImpl implements TipoContratacaoService {

    private final Logger log = LoggerFactory.getLogger(TipoContratacaoServiceImpl.class);

    private final TipoContratacaoRepository tipoContratacaoRepository;

    public TipoContratacaoServiceImpl(TipoContratacaoRepository tipoContratacaoRepository) {
        this.tipoContratacaoRepository = tipoContratacaoRepository;
    }

    /**
     * Save a tipoContratacao.
     *
     * @param tipoContratacao the entity to save
     * @return the persisted entity
     */
    @Override
    public TipoContratacao save(TipoContratacao tipoContratacao) {
        log.debug("Request to save TipoContratacao : {}", tipoContratacao);
        return tipoContratacaoRepository.save(tipoContratacao);
    }

    /**
     * Get all the tipoContratacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TipoContratacao> findAll(Pageable pageable) {
        log.debug("Request to get all TipoContratacaos");
        return tipoContratacaoRepository.findAll(pageable);
    }



    /**
     *  get all the tipoContratacaos where Colaborador is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<TipoContratacao> findAllWhereColaboradorIsNull() {
        log.debug("Request to get all tipoContratacaos where Colaborador is null");
        return StreamSupport
            .stream(tipoContratacaoRepository.findAll().spliterator(), false)
            .filter(tipoContratacao -> tipoContratacao.getColaborador() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one tipoContratacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TipoContratacao> findOne(Long id) {
        log.debug("Request to get TipoContratacao : {}", id);
        return tipoContratacaoRepository.findById(id);
    }

    /**
     * Delete the tipoContratacao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TipoContratacao : {}", id);
        tipoContratacaoRepository.deleteById(id);
    }
}
