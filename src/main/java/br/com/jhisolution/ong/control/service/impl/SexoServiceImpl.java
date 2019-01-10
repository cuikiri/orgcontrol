package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.SexoService;
import br.com.jhisolution.ong.control.domain.Sexo;
import br.com.jhisolution.ong.control.repository.SexoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Sexo.
 */
@Service
@Transactional
public class SexoServiceImpl implements SexoService {

    private final Logger log = LoggerFactory.getLogger(SexoServiceImpl.class);

    private final SexoRepository sexoRepository;

    public SexoServiceImpl(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }

    /**
     * Save a sexo.
     *
     * @param sexo the entity to save
     * @return the persisted entity
     */
    @Override
    public Sexo save(Sexo sexo) {
        log.debug("Request to save Sexo : {}", sexo);
        return sexoRepository.save(sexo);
    }

    /**
     * Get all the sexos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Sexo> findAll(Pageable pageable) {
        log.debug("Request to get all Sexos");
        return sexoRepository.findAll(pageable);
    }


    /**
     * Get one sexo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Sexo> findOne(Long id) {
        log.debug("Request to get Sexo : {}", id);
        return sexoRepository.findById(id);
    }

    /**
     * Delete the sexo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sexo : {}", id);
        sexoRepository.deleteById(id);
    }
}
