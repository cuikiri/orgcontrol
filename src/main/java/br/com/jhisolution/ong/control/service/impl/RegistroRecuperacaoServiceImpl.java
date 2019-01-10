package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.RegistroRecuperacaoService;
import br.com.jhisolution.ong.control.domain.RegistroRecuperacao;
import br.com.jhisolution.ong.control.repository.RegistroRecuperacaoRepository;
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
 * Service Implementation for managing RegistroRecuperacao.
 */
@Service
@Transactional
public class RegistroRecuperacaoServiceImpl implements RegistroRecuperacaoService {

    private final Logger log = LoggerFactory.getLogger(RegistroRecuperacaoServiceImpl.class);

    private final RegistroRecuperacaoRepository registroRecuperacaoRepository;

    public RegistroRecuperacaoServiceImpl(RegistroRecuperacaoRepository registroRecuperacaoRepository) {
        this.registroRecuperacaoRepository = registroRecuperacaoRepository;
    }

    /**
     * Save a registroRecuperacao.
     *
     * @param registroRecuperacao the entity to save
     * @return the persisted entity
     */
    @Override
    public RegistroRecuperacao save(RegistroRecuperacao registroRecuperacao) {
        log.debug("Request to save RegistroRecuperacao : {}", registroRecuperacao);
        return registroRecuperacaoRepository.save(registroRecuperacao);
    }

    /**
     * Get all the registroRecuperacaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RegistroRecuperacao> findAll(Pageable pageable) {
        log.debug("Request to get all RegistroRecuperacaos");
        return registroRecuperacaoRepository.findAll(pageable);
    }



    /**
     *  get all the registroRecuperacaos where Diario is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<RegistroRecuperacao> findAllWhereDiarioIsNull() {
        log.debug("Request to get all registroRecuperacaos where Diario is null");
        return StreamSupport
            .stream(registroRecuperacaoRepository.findAll().spliterator(), false)
            .filter(registroRecuperacao -> registroRecuperacao.getDiario() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one registroRecuperacao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RegistroRecuperacao> findOne(Long id) {
        log.debug("Request to get RegistroRecuperacao : {}", id);
        return registroRecuperacaoRepository.findById(id);
    }

    /**
     * Delete the registroRecuperacao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RegistroRecuperacao : {}", id);
        registroRecuperacaoRepository.deleteById(id);
    }
}
