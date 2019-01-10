import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IReligiao } from 'app/shared/model/religiao.model';
import { ReligiaoService } from './religiao.service';

@Component({
    selector: 'jhi-religiao-update',
    templateUrl: './religiao-update.component.html'
})
export class ReligiaoUpdateComponent implements OnInit {
    religiao: IReligiao;
    isSaving: boolean;

    constructor(private religiaoService: ReligiaoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ religiao }) => {
            this.religiao = religiao;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.religiao.id !== undefined) {
            this.subscribeToSaveResponse(this.religiaoService.update(this.religiao));
        } else {
            this.subscribeToSaveResponse(this.religiaoService.create(this.religiao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReligiao>>) {
        result.subscribe((res: HttpResponse<IReligiao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
