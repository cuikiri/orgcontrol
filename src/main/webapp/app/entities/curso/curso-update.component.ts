import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICurso } from 'app/shared/model/curso.model';
import { CursoService } from './curso.service';

@Component({
    selector: 'jhi-curso-update',
    templateUrl: './curso-update.component.html'
})
export class CursoUpdateComponent implements OnInit {
    curso: ICurso;
    isSaving: boolean;

    constructor(private cursoService: CursoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ curso }) => {
            this.curso = curso;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.curso.id !== undefined) {
            this.subscribeToSaveResponse(this.cursoService.update(this.curso));
        } else {
            this.subscribeToSaveResponse(this.cursoService.create(this.curso));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICurso>>) {
        result.subscribe((res: HttpResponse<ICurso>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
