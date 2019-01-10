import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IFotoDocumento } from 'app/shared/model/foto-documento.model';

@Component({
    selector: 'jhi-foto-documento-detail',
    templateUrl: './foto-documento-detail.component.html'
})
export class FotoDocumentoDetailComponent implements OnInit {
    fotoDocumento: IFotoDocumento;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ fotoDocumento }) => {
            this.fotoDocumento = fotoDocumento;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
