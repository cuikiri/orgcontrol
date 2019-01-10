import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';
import { FotoAcompanhamentoAlunoService } from './foto-acompanhamento-aluno.service';
import { FotoAcompanhamentoAlunoComponent } from './foto-acompanhamento-aluno.component';
import { FotoAcompanhamentoAlunoDetailComponent } from './foto-acompanhamento-aluno-detail.component';
import { FotoAcompanhamentoAlunoUpdateComponent } from './foto-acompanhamento-aluno-update.component';
import { FotoAcompanhamentoAlunoDeletePopupComponent } from './foto-acompanhamento-aluno-delete-dialog.component';
import { IFotoAcompanhamentoAluno } from 'app/shared/model/foto-acompanhamento-aluno.model';

@Injectable({ providedIn: 'root' })
export class FotoAcompanhamentoAlunoResolve implements Resolve<IFotoAcompanhamentoAluno> {
    constructor(private service: FotoAcompanhamentoAlunoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FotoAcompanhamentoAluno> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FotoAcompanhamentoAluno>) => response.ok),
                map((fotoAcompanhamentoAluno: HttpResponse<FotoAcompanhamentoAluno>) => fotoAcompanhamentoAluno.body)
            );
        }
        return of(new FotoAcompanhamentoAluno());
    }
}

export const fotoAcompanhamentoAlunoRoute: Routes = [
    {
        path: 'foto-acompanhamento-aluno',
        component: FotoAcompanhamentoAlunoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.fotoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'foto-acompanhamento-aluno/:id/view',
        component: FotoAcompanhamentoAlunoDetailComponent,
        resolve: {
            fotoAcompanhamentoAluno: FotoAcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fotoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'foto-acompanhamento-aluno/new',
        component: FotoAcompanhamentoAlunoUpdateComponent,
        resolve: {
            fotoAcompanhamentoAluno: FotoAcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fotoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'foto-acompanhamento-aluno/:id/edit',
        component: FotoAcompanhamentoAlunoUpdateComponent,
        resolve: {
            fotoAcompanhamentoAluno: FotoAcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fotoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fotoAcompanhamentoAlunoPopupRoute: Routes = [
    {
        path: 'foto-acompanhamento-aluno/:id/delete',
        component: FotoAcompanhamentoAlunoDeletePopupComponent,
        resolve: {
            fotoAcompanhamentoAluno: FotoAcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fotoAcompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
