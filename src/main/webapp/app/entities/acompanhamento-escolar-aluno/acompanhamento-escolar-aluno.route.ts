import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';
import { AcompanhamentoEscolarAlunoService } from './acompanhamento-escolar-aluno.service';
import { AcompanhamentoEscolarAlunoComponent } from './acompanhamento-escolar-aluno.component';
import { AcompanhamentoEscolarAlunoDetailComponent } from './acompanhamento-escolar-aluno-detail.component';
import { AcompanhamentoEscolarAlunoUpdateComponent } from './acompanhamento-escolar-aluno-update.component';
import { AcompanhamentoEscolarAlunoDeletePopupComponent } from './acompanhamento-escolar-aluno-delete-dialog.component';
import { IAcompanhamentoEscolarAluno } from 'app/shared/model/acompanhamento-escolar-aluno.model';

@Injectable({ providedIn: 'root' })
export class AcompanhamentoEscolarAlunoResolve implements Resolve<IAcompanhamentoEscolarAluno> {
    constructor(private service: AcompanhamentoEscolarAlunoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AcompanhamentoEscolarAluno> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AcompanhamentoEscolarAluno>) => response.ok),
                map((acompanhamentoEscolarAluno: HttpResponse<AcompanhamentoEscolarAluno>) => acompanhamentoEscolarAluno.body)
            );
        }
        return of(new AcompanhamentoEscolarAluno());
    }
}

export const acompanhamentoEscolarAlunoRoute: Routes = [
    {
        path: 'acompanhamento-escolar-aluno',
        component: AcompanhamentoEscolarAlunoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.acompanhamentoEscolarAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-escolar-aluno/:id/view',
        component: AcompanhamentoEscolarAlunoDetailComponent,
        resolve: {
            acompanhamentoEscolarAluno: AcompanhamentoEscolarAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoEscolarAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-escolar-aluno/new',
        component: AcompanhamentoEscolarAlunoUpdateComponent,
        resolve: {
            acompanhamentoEscolarAluno: AcompanhamentoEscolarAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoEscolarAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-escolar-aluno/:id/edit',
        component: AcompanhamentoEscolarAlunoUpdateComponent,
        resolve: {
            acompanhamentoEscolarAluno: AcompanhamentoEscolarAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoEscolarAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const acompanhamentoEscolarAlunoPopupRoute: Routes = [
    {
        path: 'acompanhamento-escolar-aluno/:id/delete',
        component: AcompanhamentoEscolarAlunoDeletePopupComponent,
        resolve: {
            acompanhamentoEscolarAluno: AcompanhamentoEscolarAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoEscolarAluno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
