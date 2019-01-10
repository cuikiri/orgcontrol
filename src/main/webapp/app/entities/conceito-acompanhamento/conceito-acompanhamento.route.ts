import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConceitoAcompanhamento } from 'app/shared/model/conceito-acompanhamento.model';
import { ConceitoAcompanhamentoService } from './conceito-acompanhamento.service';
import { ConceitoAcompanhamentoComponent } from './conceito-acompanhamento.component';
import { ConceitoAcompanhamentoDetailComponent } from './conceito-acompanhamento-detail.component';
import { ConceitoAcompanhamentoUpdateComponent } from './conceito-acompanhamento-update.component';
import { ConceitoAcompanhamentoDeletePopupComponent } from './conceito-acompanhamento-delete-dialog.component';
import { IConceitoAcompanhamento } from 'app/shared/model/conceito-acompanhamento.model';

@Injectable({ providedIn: 'root' })
export class ConceitoAcompanhamentoResolve implements Resolve<IConceitoAcompanhamento> {
    constructor(private service: ConceitoAcompanhamentoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ConceitoAcompanhamento> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ConceitoAcompanhamento>) => response.ok),
                map((conceitoAcompanhamento: HttpResponse<ConceitoAcompanhamento>) => conceitoAcompanhamento.body)
            );
        }
        return of(new ConceitoAcompanhamento());
    }
}

export const conceitoAcompanhamentoRoute: Routes = [
    {
        path: 'conceito-acompanhamento',
        component: ConceitoAcompanhamentoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.conceitoAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conceito-acompanhamento/:id/view',
        component: ConceitoAcompanhamentoDetailComponent,
        resolve: {
            conceitoAcompanhamento: ConceitoAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conceitoAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conceito-acompanhamento/new',
        component: ConceitoAcompanhamentoUpdateComponent,
        resolve: {
            conceitoAcompanhamento: ConceitoAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conceitoAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conceito-acompanhamento/:id/edit',
        component: ConceitoAcompanhamentoUpdateComponent,
        resolve: {
            conceitoAcompanhamento: ConceitoAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conceitoAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const conceitoAcompanhamentoPopupRoute: Routes = [
    {
        path: 'conceito-acompanhamento/:id/delete',
        component: ConceitoAcompanhamentoDeletePopupComponent,
        resolve: {
            conceitoAcompanhamento: ConceitoAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conceitoAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
