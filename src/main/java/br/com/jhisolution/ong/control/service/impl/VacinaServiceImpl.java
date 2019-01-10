package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.VacinaService;
import br.com.jhisolution.ong.control.domain.Vacina;
import br.com.jhisolution.ong.control.repository.VacinaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Vacina.
 */
@Service
@Transactional
public class VacinaServiceImpl implements VacinaService {

    private final Logger log = LoggerFactory.getLogger(VacinaServiceImpl.class);

    private final VacinaRepository vacinaRepository;

    public VacinaServiceImpl(VacinaRepository vacinaRepository) {
        this.vacinaRepository = vacinaRepository;
    }

    /**
     * Save a vacina.
     *
     * @param vacina the entity to save
     * @return the persisted entity
     */
    @Override
    public Vacina save(Vacina vacina) {
        log.debug("Request to save Vacina : {}", vacina);
        return vacinaRepository.save(vacina);
    }

    /**
     * Get all the vacinas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Vacina> findAll(Pageable pageable) {
        log.debug("Request to get all Vacinas");
        return vacinaRepository.findAll(pageable);
    }


    /**
     * Get one vacina by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Vacina> findOne(Long id) {
        log.debug("Request to get Vacina : {}", id);
        return vacinaRepository.findById(id);
    }

    /**
     * Delete the vacina by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vacina : {}", id);
        vacinaRepository.deleteById(id);
    }
}
