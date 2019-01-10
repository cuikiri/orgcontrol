import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';
import { AcompanhamentoAlunoService } from './acompanhamento-aluno.service';
import { AcompanhamentoAlunoComponent } from './acompanhamento-aluno.component';
import { AcompanhamentoAlunoDetailComponent } from './acompanhamento-aluno-detail.component';
import { AcompanhamentoAlunoUpdateComponent } from './acompanhamento-aluno-update.component';
import { AcompanhamentoAlunoDeletePopupComponent } from './acompanhamento-aluno-delete-dialog.component';
import { IAcompanhamentoAluno } from 'app/shared/model/acompanhamento-aluno.model';

@Injectable({ providedIn: 'root' })
export class AcompanhamentoAlunoResolve implements Resolve<IAcompanhamentoAluno> {
    constructor(private service: AcompanhamentoAlunoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AcompanhamentoAluno> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AcompanhamentoAluno>) => response.ok),
                map((acompanhamentoAluno: HttpResponse<AcompanhamentoAluno>) => acompanhamentoAluno.body)
            );
        }
        return of(new AcompanhamentoAluno());
    }
}

export const acompanhamentoAlunoRoute: Routes = [
    {
        path: 'acompanhamento-aluno',
        component: AcompanhamentoAlunoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.acompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-aluno/:id/view',
        component: AcompanhamentoAlunoDetailComponent,
        resolve: {
            acompanhamentoAluno: AcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-aluno/new',
        component: AcompanhamentoAlunoUpdateComponent,
        resolve: {
            acompanhamentoAluno: AcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-aluno/:id/edit',
        component: AcompanhamentoAlunoUpdateComponent,
        resolve: {
            acompanhamentoAluno: AcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const acompanhamentoAlunoPopupRoute: Routes = [
    {
        path: 'acompanhamento-aluno/:id/delete',
        component: AcompanhamentoAlunoDeletePopupComponent,
        resolve: {
            acompanhamentoAluno: AcompanhamentoAlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoAluno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
