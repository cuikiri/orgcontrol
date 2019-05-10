package br.com.jhisolution.ong.control.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.jhisolution.ong.control.domain.Documento;
import br.com.jhisolution.ong.control.domain.FotoDocumento;
import br.com.jhisolution.ong.control.service.DocumentoService;
import br.com.jhisolution.ong.control.service.FotoDocumentoService;
import br.com.jhisolution.ong.control.web.rest.errors.BadRequestAlertException;
import br.com.jhisolution.ong.control.web.rest.util.HeaderUtil;
import br.com.jhisolution.ong.control.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing FotoDocumento.
 */
@RestController
@RequestMapping("/api")
public class FotoDocumentoResource {

    private final Logger log = LoggerFactory.getLogger(FotoDocumentoResource.class);

    private static final String ENTITY_NAME = "fotoDocumento";

    private final FotoDocumentoService fotoDocumentoService;
    
    private final DocumentoService documentoService;

    public FotoDocumentoResource(FotoDocumentoService fotoDocumentoService, DocumentoService documentoService) {
        this.fotoDocumentoService = fotoDocumentoService;
        this.documentoService = documentoService;
    }

    /**
     * POST  /foto-documentos : Create a new fotoDocumento.
     *
     * @param fotoDocumento the fotoDocumento to create
     * @return the ResponseEntity with status 201 (Created) and with body the new fotoDocumento, or with status 400 (Bad Request) if the fotoDocumento has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/foto-documentos")
    @Timed
    public ResponseEntity<FotoDocumento> createFotoDocumento(@Valid @RequestBody FotoDocumento fotoDocumento) throws URISyntaxException {
        log.debug("REST request to save FotoDocumento : {}", fotoDocumento);
        if (fotoDocumento.getId() != null) {
            throw new BadRequestAlertException("A new fotoDocumento cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        System.out.println("============================================================================");
        System.out.println("DOCUMENTO = " + fotoDocumento.getDocumento().getId());
        
        if (Optional.ofNullable(fotoDocumento.getDocumento()).isPresent()) {
        	Optional<Documento> opt = documentoService.findOne(fotoDocumento.getDocumento().getId());
        	
        	if (opt.isPresent()) {
        		Documento doc = opt.get();
        		System.out.println("============================================================================p1");
        		fotoDocumento.setDocumento(doc);
        		System.out.println("============================================================================p3");
        		FotoDocumento result = fotoDocumentoService.save(fotoDocumento);
        		System.out.println("============================================================================");
                System.out.println("FOTO DOCUMENTO = " + result.getId());
                return ResponseEntity.created(new URI("/api/foto-documentos/" + result.getId()))
                    .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                    .body(result);
        	}
        } else {
        	FotoDocumento result = fotoDocumentoService.save(fotoDocumento);
            return ResponseEntity.created(new URI("/api/foto-documentos/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
        }
        
        return null;
    }

    /**
     * PUT  /foto-documentos : Updates an existing fotoDocumento.
     *
     * @param fotoDocumento the fotoDocumento to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated fotoDocumento,
     * or with status 400 (Bad Request) if the fotoDocumento is not valid,
     * or with status 500 (Internal Server Error) if the fotoDocumento couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/foto-documentos")
    @Timed
    public ResponseEntity<FotoDocumento> updateFotoDocumento(@Valid @RequestBody FotoDocumento fotoDocumento) throws URISyntaxException {
        log.debug("REST request to update FotoDocumento : {}", fotoDocumento);
        if (fotoDocumento.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FotoDocumento result = fotoDocumentoService.save(fotoDocumento);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, fotoDocumento.getId().toString()))
            .body(result);
    }

    /**
     * GET  /foto-documentos : get all the fotoDocumentos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of fotoDocumentos in body
     */
    @GetMapping("/foto-documentos")
    @Timed
    public ResponseEntity<List<FotoDocumento>> getAllFotoDocumentos(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("documento-is-null".equals(filter)) {
            log.debug("REST request to get all FotoDocumentos where documento is null");
            return new ResponseEntity<>(fotoDocumentoService.findAllWhereDocumentoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of FotoDocumentos");
        Page<FotoDocumento> page = fotoDocumentoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/foto-documentos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    
    /**
     * GET  /foto-documentos : get all the fotoDocumentos.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of fotoDocumentos in body
     */
    @GetMapping("/foto-documentos/documento/{id}")
    @Timed
    public ResponseEntity<List<FotoDocumento>> getAllFotoDocumentosByDocumento(Pageable pageable, @RequestParam(required = false) String filter, @PathVariable Long id) {
        if ("documento-is-null".equals(filter)) {
            log.debug("REST request to get all FotoDocumentos where documento is null");
            return new ResponseEntity<>(fotoDocumentoService.findAllWhereDocumentoIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of FotoDocumentos");
        Page<FotoDocumento> page = fotoDocumentoService.findAllByDocumento(pageable, id);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/foto-documentos");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /foto-documentos/:id : get the "id" fotoDocumento.
     *
     * @param id the id of the fotoDocumento to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the fotoDocumento, or with status 404 (Not Found)
     */
    @GetMapping("/foto-documentos/{id}")
    @Timed
    public ResponseEntity<FotoDocumento> getFotoDocumento(@PathVariable Long id) {
        log.debug("REST request to get FotoDocumento : {}", id);
        Optional<FotoDocumento> fotoDocumento = fotoDocumentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fotoDocumento);
    }

    /**
     * DELETE  /foto-documentos/:id : delete the "id" fotoDocumento.
     *
     * @param id the id of the fotoDocumento to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/foto-documentos/{id}")
    @Timed
    public ResponseEntity<Void> deleteFotoDocumento(@PathVariable Long id) {
        log.debug("REST request to delete FotoDocumento : {}", id);
        fotoDocumentoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
