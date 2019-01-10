package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.FechamentoBimestreService;
import br.com.jhisolution.ong.control.domain.FechamentoBimestre;
import br.com.jhisolution.ong.control.repository.FechamentoBimestreRepository;
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
 * Service Implementation for managing FechamentoBimestre.
 */
@Service
@Transactional
public class FechamentoBimestreServiceImpl implements FechamentoBimestreService {

    private final Logger log = LoggerFactory.getLogger(FechamentoBimestreServiceImpl.class);

    private final FechamentoBimestreRepository fechamentoBimestreRepository;

    public FechamentoBimestreServiceImpl(FechamentoBimestreRepository fechamentoBimestreRepository) {
        this.fechamentoBimestreRepository = fechamentoBimestreRepository;
    }

    /**
     * Save a fechamentoBimestre.
     *
     * @param fechamentoBimestre the entity to save
     * @return the persisted entity
     */
    @Override
    public FechamentoBimestre save(FechamentoBimestre fechamentoBimestre) {
        log.debug("Request to save FechamentoBimestre : {}", fechamentoBimestre);
        return fechamentoBimestreRepository.save(fechamentoBimestre);
    }

    /**
     * Get all the fechamentoBimestres.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FechamentoBimestre> findAll(Pageable pageable) {
        log.debug("Request to get all FechamentoBimestres");
        return fechamentoBimestreRepository.findAll(pageable);
    }

    /**
     * Get all the FechamentoBimestre with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<FechamentoBimestre> findAllWithEagerRelationships(Pageable pageable) {
        return fechamentoBimestreRepository.findAllWithEagerRelationships(pageable);
    }
    


    /**
     *  get all the fechamentoBimestres where Bimestre is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<FechamentoBimestre> findAllWhereBimestreIsNull() {
        log.debug("Request to get all fechamentoBimestres where Bimestre is null");
        return StreamSupport
            .stream(fechamentoBimestreRepository.findAll().spliterator(), false)
            .filter(fechamentoBimestre -> fechamentoBimestre.getBimestre() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one fechamentoBimestre by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FechamentoBimestre> findOne(Long id) {
        log.debug("Request to get FechamentoBimestre : {}", id);
        return fechamentoBimestreRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the fechamentoBimestre by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FechamentoBimestre : {}", id);
        fechamentoBimestreRepository.deleteById(id);
    }
}
