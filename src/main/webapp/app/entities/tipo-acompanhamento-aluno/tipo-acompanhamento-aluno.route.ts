import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';
import { TipoAcompanhamentoAlunoService } from './tipo-acompanhamento-aluno.service';
import { TipoAcompanhamentoAlunoComponent } from './tipo-acompanhamento-aluno.component';
import { TipoAcompanhamentoAlunoDetailComponent } from './tipo-acompanhamento-aluno-detail.component';
import { TipoAcompanhamentoAlunoUpdateComponent } from './tipo-acompanhamento-aluno-update.component';
import { TipoAcompanhamentoAlunoDeletePopupComponent } from './tipo-acompanhamento-aluno-delete-dialog.component';
import { ITipoAcompanhamentoAluno } from 'app/shared/model/tipo-acompanhamento-aluno.model';

@Injectable({ providedIn: 'root' })
export class TipoAcompanhamentoAlunoResolve implements Resolve<ITipoAcompanhamentoAluno> {
    constructor(private service: TipoAcompanhamentoAlunoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoAcompanhamentoAluno> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoAcompanhamentoAluno>) => response.ok),
                map((tipoAcompanhamentoAluno: HttpResponse<TipoAcompanhamentoAluno>) => tipoAcompanhamentoAluno.body)
            );
        }
        return of(new TipoAcompanhamentoAluno());
    }
}

export const tipoAcompanhamentoAlunoRoute: Routes = [
    {
        path: 'tipo-acompanhamento-aluno',
        component: TipoAcompanhamentoAlunoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-acompanhamento-aluno/:id/view',
        component: TipoAcompanhamentoAlunoDetailComponent,
        resolve: {
            tipoAcompanhamentoAluno: TipoAcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-acompanhamento-aluno/new',
        component: TipoAcompanhamentoAlunoUpdateComponent,
        resolve: {
            tipoAcompanhamentoAluno: TipoAcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-acompanhamento-aluno/:id/edit',
        component: TipoAcompanhamentoAlunoUpdateComponent,
        resolve: {
            tipoAcompanhamentoAluno: TipoAcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoAcompanhamentoAlunoPopupRoute: Routes = [
    {
        path: 'tipo-acompanhamento-aluno/:id/delete',
        component: TipoAcompanhamentoAlunoDeletePopupComponent,
        resolve: {
            tipoAcompanhamentoAluno: TipoAcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
