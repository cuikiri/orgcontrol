import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';
import { FechamentoBimestreService } from './fechamento-bimestre.service';
import { IConceito } from 'app/shared/model/conceito.model';
import { ConceitoService } from 'app/entities/conceito';
import { IAluno } from 'app/shared/model/aluno.model';
import { AlunoService } from 'app/entities/aluno';
import { IBimestre } from 'app/shared/model/bimestre.model';
import { BimestreService } from 'app/entities/bimestre';

@Component({
    selector: 'jhi-fechamento-bimestre-update',
    templateUrl: './fechamento-bimestre-update.component.html'
})
export class FechamentoBimestreUpdateComponent implements OnInit {
    fechamentoBimestre: IFechamentoBimestre;
    isSaving: boolean;

    conceitos: IConceito[];

    alunos: IAluno[];

    bimestres: IBimestre[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private fechamentoBimestreService: FechamentoBimestreService,
        private conceitoService: ConceitoService,
        private alunoService: AlunoService,
        private bimestreService: BimestreService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ fechamentoBimestre }) => {
            this.fechamentoBimestre = fechamentoBimestre;
        });
        this.conceitoService.query({ filter: 'fechamentobimestre-is-null' }).subscribe(
            (res: HttpResponse<IConceito[]>) => {
                if (!this.fechamentoBimestre.conceito || !this.fechamentoBimestre.conceito.id) {
                    this.conceitos = res.body;
                } else {
                    this.conceitoService.find(this.fechamentoBimestre.conceito.id).subscribe(
                        (subRes: HttpResponse<IConceito>) => {
                            this.conceitos = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.alunoService.query().subscribe(
            (res: HttpResponse<IAluno[]>) => {
                this.alunos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.bimestreService.query().subscribe(
            (res: HttpResponse<IBimestre[]>) => {
                this.bimestres = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.fechamentoBimestre.id !== undefined) {
            this.subscribeToSaveResponse(this.fechamentoBimestreService.update(this.fechamentoBimestre));
        } else {
            this.subscribeToSaveResponse(this.fechamentoBimestreService.create(this.fechamentoBimestre));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFechamentoBimestre>>) {
        result.subscribe((res: HttpResponse<IFechamentoBimestre>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackConceitoById(index: number, item: IConceito) {
        return item.id;
    }

    trackAlunoById(index: number, item: IAluno) {
        return item.id;
    }

    trackBimestreById(index: number, item: IBimestre) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
