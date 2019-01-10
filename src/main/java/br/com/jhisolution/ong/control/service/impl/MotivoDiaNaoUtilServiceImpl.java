package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.MotivoDiaNaoUtilService;
import br.com.jhisolution.ong.control.domain.MotivoDiaNaoUtil;
import br.com.jhisolution.ong.control.repository.MotivoDiaNaoUtilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MotivoDiaNaoUtil.
 */
@Service
@Transactional
public class MotivoDiaNaoUtilServiceImpl implements MotivoDiaNaoUtilService {

    private final Logger log = LoggerFactory.getLogger(MotivoDiaNaoUtilServiceImpl.class);

    private final MotivoDiaNaoUtilRepository motivoDiaNaoUtilRepository;

    public MotivoDiaNaoUtilServiceImpl(MotivoDiaNaoUtilRepository motivoDiaNaoUtilRepository) {
        this.motivoDiaNaoUtilRepository = motivoDiaNaoUtilRepository;
    }

    /**
     * Save a motivoDiaNaoUtil.
     *
     * @param motivoDiaNaoUtil the entity to save
     * @return the persisted entity
     */
    @Override
    public MotivoDiaNaoUtil save(MotivoDiaNaoUtil motivoDiaNaoUtil) {
        log.debug("Request to save MotivoDiaNaoUtil : {}", motivoDiaNaoUtil);
        return motivoDiaNaoUtilRepository.save(motivoDiaNaoUtil);
    }

    /**
     * Get all the motivoDiaNaoUtils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MotivoDiaNaoUtil> findAll(Pageable pageable) {
        log.debug("Request to get all MotivoDiaNaoUtils");
        return motivoDiaNaoUtilRepository.findAll(pageable);
    }


    /**
     * Get one motivoDiaNaoUtil by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MotivoDiaNaoUtil> findOne(Long id) {
        log.debug("Request to get MotivoDiaNaoUtil : {}", id);
        return motivoDiaNaoUtilRepository.findById(id);
    }

    /**
     * Delete the motivoDiaNaoUtil by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MotivoDiaNaoUtil : {}", id);
        motivoDiaNaoUtilRepository.deleteById(id);
    }
}
