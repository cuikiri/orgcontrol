package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.CaracteristicasPsiquicasService;
import br.com.jhisolution.ong.control.domain.CaracteristicasPsiquicas;
import br.com.jhisolution.ong.control.repository.CaracteristicasPsiquicasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CaracteristicasPsiquicas.
 */
@Service
@Transactional
public class CaracteristicasPsiquicasServiceImpl implements CaracteristicasPsiquicasService {

    private final Logger log = LoggerFactory.getLogger(CaracteristicasPsiquicasServiceImpl.class);

    private final CaracteristicasPsiquicasRepository caracteristicasPsiquicasRepository;

    public CaracteristicasPsiquicasServiceImpl(CaracteristicasPsiquicasRepository caracteristicasPsiquicasRepository) {
        this.caracteristicasPsiquicasRepository = caracteristicasPsiquicasRepository;
    }

    /**
     * Save a caracteristicasPsiquicas.
     *
     * @param caracteristicasPsiquicas the entity to save
     * @return the persisted entity
     */
    @Override
    public CaracteristicasPsiquicas save(CaracteristicasPsiquicas caracteristicasPsiquicas) {
        log.debug("Request to save CaracteristicasPsiquicas : {}", caracteristicasPsiquicas);
        return caracteristicasPsiquicasRepository.save(caracteristicasPsiquicas);
    }

    /**
     * Get all the caracteristicasPsiquicas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CaracteristicasPsiquicas> findAll(Pageable pageable) {
        log.debug("Request to get all CaracteristicasPsiquicas");
        return caracteristicasPsiquicasRepository.findAll(pageable);
    }


    /**
     * Get one caracteristicasPsiquicas by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CaracteristicasPsiquicas> findOne(Long id) {
        log.debug("Request to get CaracteristicasPsiquicas : {}", id);
        return caracteristicasPsiquicasRepository.findById(id);
    }

    /**
     * Delete the caracteristicasPsiquicas by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CaracteristicasPsiquicas : {}", id);
        caracteristicasPsiquicasRepository.deleteById(id);
    }
}
