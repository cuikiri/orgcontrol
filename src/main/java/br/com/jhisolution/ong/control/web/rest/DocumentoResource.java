package br.com.jhisolution.ong.control.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.jhisolution.ong.control.domain.Documento;
import br.com.jhisolution.ong.control.domain.User;
import br.com.jhisolution.ong.control.repository.UserRepository;
import br.com.jhisolution.ong.control.security.SecurityUtils;
import br.com.jhisolution.ong.control.service.DocumentoService;
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
 * REST controller for managing Documento.
 */
@RestController
@RequestMapping("/api")
public class DocumentoResource {

    private final Logger log = LoggerFactory.getLogger(DocumentoResource.class);

    private static final String ENTITY_NAME = "documento";

    private final DocumentoService documentoService;
    
    private final UserRepository userRepository;

    public DocumentoResource(DocumentoService documentoService,
    		UserRepository userRepository) {
        this.documentoService = documentoService;
        this.userRepository = userRepository;
    }

    /**
     * POST  /documentos : Create a new documento.
     *
     * @param documento the documento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documento, or with status 400 (Bad Request) if the documento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/documentos")
    @Timed
    public ResponseEntity<Documento> createDocumento(@Valid @RequestBody Documento documento) throws URISyntaxException {
        log.debug("REST request to save Documento : {}", documento);
        if (documento.getId() != null) {
            throw new BadRequestAlertException("A new documento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        
        if (Optional.ofNullable(user.get().getPessoa()).isPresent()) {
        	documento.setPessoa(user.get().getPessoa());
        }
        
        Documento result = documentoService.save(documento);
        return ResponseEntity.created(new URI("/api/documentos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /documentos : Updates an existing documento.
     *
     * @param documento the documento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documento,
     * or with status 400 (Bad Request) if the documento is not valid,
     * or with status 500 (Internal Server Error) if the documento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/documentos")
    @Timed
    public ResponseEntity<Documento> updateDocumento(@Valid @RequestBody Documento documento) throws URISyntaxException {
        log.debug("REST request to update Documento : {}", documento);
        if (documento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Documento result = documentoService.save(documento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /documentos : get all the documentos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of documentos in body
     */
    @GetMapping("/documentos")
    @Timed
    public ResponseEntity<List<Documento>> getAllDocumentos(Pageable pageable) {
        log.debug("REST request to get a page of Documentos");
        Page<Documento> page = documentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/documentos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /documentos : get all the documentos.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of documentos in body
     */
    @GetMapping("/documentos/pessoa")
    @Timed
    public ResponseEntity<List<Documento>> getAllDocumentosByPessoa(Pageable pageable) {
        log.debug("REST request to get a page of Documentos");
        
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        
        Page<Documento> page = documentoService.findAllByPessoa(pageable, user.get().getPessoa());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/documentos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * GET  /documentos/:id : get the "id" documento.
     *
     * @param id the id of the documento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the documento, or with status 404 (Not Found)
     */
    @GetMapping("/documentos/{id}")
    @Timed
    public ResponseEntity<Documento> getDocumento(@PathVariable Long id) {
        log.debug("REST request to get Documento : {}", id);
        Optional<Documento> documento = documentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documento);
    }

    /**
     * DELETE  /documentos/:id : delete the "id" documento.
     *
     * @param id the id of the documento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/documentos/{id}")
    @Timed
    public ResponseEntity<Void> deleteDocumento(@PathVariable Long id) {
        log.debug("REST request to delete Documento : {}", id);
        documentoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
