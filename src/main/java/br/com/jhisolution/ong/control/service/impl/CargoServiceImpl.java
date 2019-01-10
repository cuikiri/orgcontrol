package br.com.jhisolution.ong.control.service.impl;

import br.com.jhisolution.ong.control.service.CargoService;
import br.com.jhisolution.ong.control.domain.Cargo;
import br.com.jhisolution.ong.control.repository.CargoRepository;
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
 * Service Implementation for managing Cargo.
 */
@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    private final Logger log = LoggerFactory.getLogger(CargoServiceImpl.class);

    private final CargoRepository cargoRepository;

    public CargoServiceImpl(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    /**
     * Save a cargo.
     *
     * @param cargo the entity to save
     * @return the persisted entity
     */
    @Override
    public Cargo save(Cargo cargo) {
        log.debug("Request to save Cargo : {}", cargo);
        return cargoRepository.save(cargo);
    }

    /**
     * Get all the cargos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Cargo> findAll(Pageable pageable) {
        log.debug("Request to get all Cargos");
        return cargoRepository.findAll(pageable);
    }



    /**
     *  get all the cargos where Candidato is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<Cargo> findAllWhereCandidatoIsNull() {
        log.debug("Request to get all cargos where Candidato is null");
        return StreamSupport
            .stream(cargoRepository.findAll().spliterator(), false)
            .filter(cargo -> cargo.getCandidato() == null)
            .collect(Collectors.toList());
    }

    /**
     * Get one cargo by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Cargo> findOne(Long id) {
        log.debug("Request to get Cargo : {}", id);
        return cargoRepository.findById(id);
    }

    /**
     * Delete the cargo by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cargo : {}", id);
        cargoRepository.deleteById(id);
    }
}
