package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.LocalizacaoService;
import br.com.jhisolution.ong.control.domain.Localizacao;
import br.com.jhisolution.ong.control.repository.LocalizacaoRepository;
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
 * Service Implementation for managing Localizacao.
 */
@Service
@Transactional
public class LocalizacaoServiceImpl implements LocalizacaoService {

    private final Logger log = LoggerFactory.getLogger(LocalizacaoServiceImpl.class);

    private final LocalizacaoRepository localizacaoRepository;

    public LocalizacaoServiceImpl(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

    /**
     * Save a localizacao.
     *
     * @param localizacao the entity to save
     * @return the persisted entity
     */
    @Override
    public Localizacao save(Localizacao localizacao) {
        log.debug("Request to save Localizacao : {}", localizacao);
        return localizacaoRepository.save(localizacao);
    }

    /**
     * Get all the localizacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Localizacao> findAll(Pageable pageable) {
        log.debug("Request to get all Localizacaos");
        return localizacaoRepository.findAll(pageable);
    }



    /**
     *  get all the localizacaos where Endereco is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Localizacao> findAllWhereEnderecoIsNull() {
        log.debug("Request to get all localizacaos where Endereco is null");
        return StreamSupport
            .stream(localizacaoRepository.findAll().spliterator(), false)
            .filter(localizacao -> localizacao.getEndereco() == null)
            .collect(Collectors.toList());
    }


    /**
     *  get all the localizacaos where Bloco is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Localizacao> findAllWhereBlocoIsNull() {
        log.debug("Request to get all localizacaos where Bloco is null");
        return StreamSupport
            .stream(localizacaoRepository.findAll().spliterator(), false)
            .filter(localizacao -> localizacao.getBloco() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one localizacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Localizacao> findOne(Long id) {
        log.debug("Request to get Localizacao : {}", id);
        return localizacaoRepository.findById(id);
    }

    /**
     * Delete the localizacao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Localizacao : {}", id);
        localizacaoRepository.deleteById(id);
    }
}
