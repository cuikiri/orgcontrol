package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Telefone;
import br.com.jhisolution.ong.control.domain.User;
import br.com.jhisolution.ong.control.repository.UserRepository;
import br.com.jhisolution.ong.control.security.SecurityUtils;
import br.com.jhisolution.ong.control.service.TelefoneService;
import br.com.jhisolution.ong.control.web.rest.errors.BadRequestAlertException;
import br.com.jhisolution.ong.control.web.rest.util.HeaderUtil;
import br.com.jhisolution.ong.control.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Telefone.
 */
@RestController
@RequestMapping("/api")
public class TelefoneResource {

    private final Logger log = LoggerFactory.getLogger(TelefoneResource.class);

    private static final String ENTITY_NAME = "telefone";

    private final TelefoneService telefoneService;
    
    private final UserRepository userRepository;

    public TelefoneResource(TelefoneService telefoneService,
    		UserRepository userRepository) {
        this.telefoneService = telefoneService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /telefones : Create a new telefone.
     *
     * @param telefone the telefone to create
     * @return the ResponseEntity with status 201 (Created) and with body the new telefone, or with status 400 (Bad Request) if the telefone has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/telefones")
    @Timed
    public ResponseEntity<Telefone> createTelefone(@Valid @RequestBody Telefone telefone) throws URISyntaxException {
        log.debug("REST request to save Telefone : {}", telefone);
        if (telefone.getId() != null) {
            throw new BadRequestAlertException("A new telefone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        
        if (Optional.ofNullable(user.get().getPessoa()).isPresent()) {
        	telefone.setPessoa(user.get().getPessoa());
        }
        
        Telefone result = telefoneService.save(telefone);
        return ResponseEntity.created(new URI("/api/telefones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /telefones : Updates an existing telefone.
     *
     * @param telefone the telefone to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated telefone,
     * or with status 400 (Bad Request) if the telefone is not valid,
     * or with status 500 (Internal Server Error) if the telefone couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/telefones")
    @Timed
    public ResponseEntity<Telefone> updateTelefone(@Valid @RequestBody Telefone telefone) throws URISyntaxException {
        log.debug("REST request to update Telefone : {}", telefone);
        if (telefone.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Telefone result = telefoneService.save(telefone);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, telefone.getId().toString()))
            .body(result);
    }

    /**
     * GET  /telefones : get all the telefones.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of telefones in body
     */
    @GetMapping("/telefones")
    @Timed
    public ResponseEntity<List<Telefone>> getAllTelefones(Pageable pageable) {
        log.debug("REST request to get a page of Telefones");
        Page<Telefone> page = telefoneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/telefones");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * GET  /telefones : get all the telefones.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of telefones in body
     */
    @GetMapping("/telefones/pessoa")
    @Timed
    public ResponseEntity<List<Telefone>> getAllTelefonesByPessoa(Pageable pageable) {
        log.debug("REST request to get a page of Telefones");
        
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        
        Page<Telefone> page = telefoneService.findAllByPessoa(pageable, user.get().getPessoa());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/telefones");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /telefones/:id : get the "id" telefone.
     *
     * @param id the id of the telefone to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the telefone, or with status 404 (Not Found)
     */
    @GetMapping("/telefones/{id}")
    @Timed
    public ResponseEntity<Telefone> getTelefone(@PathVariable Long id) {
        log.debug("REST request to get Telefone : {}", id);
        Optional<Telefone> telefone = telefoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(telefone);
    }

    /**
     * DELETE  /telefones/:id : delete the "id" telefone.
     *
     * @param id the id of the telefone to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/telefones/{id}")
    @Timed
    public ResponseEntity<Void> deleteTelefone(@PathVariable Long id) {
        log.debug("REST request to delete Telefone : {}", id);
        telefoneService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
