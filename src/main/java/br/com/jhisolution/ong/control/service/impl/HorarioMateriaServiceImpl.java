package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.HorarioMateriaService;
import br.com.jhisolution.ong.control.domain.HorarioMateria;
import br.com.jhisolution.ong.control.repository.HorarioMateriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing HorarioMateria.
 */
@Service
@Transactional
public class HorarioMateriaServiceImpl implements HorarioMateriaService {

    private final Logger log = LoggerFactory.getLogger(HorarioMateriaServiceImpl.class);

    private final HorarioMateriaRepository horarioMateriaRepository;

    public HorarioMateriaServiceImpl(HorarioMateriaRepository horarioMateriaRepository) {
        this.horarioMateriaRepository = horarioMateriaRepository;
    }

    /**
     * Save a horarioMateria.
     *
     * @param horarioMateria the entity to save
     * @return the persisted entity
     */
    @Override
    public HorarioMateria save(HorarioMateria horarioMateria) {
        log.debug("Request to save HorarioMateria : {}", horarioMateria);
        return horarioMateriaRepository.save(horarioMateria);
    }

    /**
     * Get all the horarioMaterias.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HorarioMateria> findAll(Pageable pageable) {
        log.debug("Request to get all HorarioMaterias");
        return horarioMateriaRepository.findAll(pageable);
    }


    /**
     * Get one horarioMateria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HorarioMateria> findOne(Long id) {
        log.debug("Request to get HorarioMateria : {}", id);
        return horarioMateriaRepository.findById(id);
    }

    /**
     * Delete the horarioMateria by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HorarioMateria : {}", id);
        horarioMateriaRepository.deleteById(id);
    }
}
