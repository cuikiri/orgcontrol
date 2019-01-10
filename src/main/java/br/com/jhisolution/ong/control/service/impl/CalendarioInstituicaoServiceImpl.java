package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.CalendarioInstituicaoService;
import br.com.jhisolution.ong.control.domain.CalendarioInstituicao;
import br.com.jhisolution.ong.control.repository.CalendarioInstituicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CalendarioInstituicao.
 */
@Service
@Transactional
public class CalendarioInstituicaoServiceImpl implements CalendarioInstituicaoService {

    private final Logger log = LoggerFactory.getLogger(CalendarioInstituicaoServiceImpl.class);

    private final CalendarioInstituicaoRepository calendarioInstituicaoRepository;

    public CalendarioInstituicaoServiceImpl(CalendarioInstituicaoRepository calendarioInstituicaoRepository) {
        this.calendarioInstituicaoRepository = calendarioInstituicaoRepository;
    }

    /**
     * Save a calendarioInstituicao.
     *
     * @param calendarioInstituicao the entity to save
     * @return the persisted entity
     */
    @Override
    public CalendarioInstituicao save(CalendarioInstituicao calendarioInstituicao) {
        log.debug("Request to save CalendarioInstituicao : {}", calendarioInstituicao);
        return calendarioInstituicaoRepository.save(calendarioInstituicao);
    }

    /**
     * Get all the calendarioInstituicaos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CalendarioInstituicao> findAll(Pageable pageable) {
        log.debug("Request to get all CalendarioInstituicaos");
        return calendarioInstituicaoRepository.findAll(pageable);
    }


    /**
     * Get one calendarioInstituicao by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CalendarioInstituicao> findOne(Long id) {
        log.debug("Request to get CalendarioInstituicao : {}", id);
        return calendarioInstituicaoRepository.findById(id);
    }

    /**
     * Delete the calendarioInstituicao by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CalendarioInstituicao : {}", id);
        calendarioInstituicaoRepository.deleteById(id);
    }
}
