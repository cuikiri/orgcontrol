import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoContratacao } from 'app/shared/model/tipo-contratacao.model';
import { TipoContratacaoService } from './tipo-contratacao.service';
import { TipoContratacaoComponent } from './tipo-contratacao.component';
import { TipoContratacaoDetailComponent } from './tipo-contratacao-detail.component';
import { TipoContratacaoUpdateComponent } from './tipo-contratacao-update.component';
import { TipoContratacaoDeletePopupComponent } from './tipo-contratacao-delete-dialog.component';
import { ITipoContratacao } from 'app/shared/model/tipo-contratacao.model';

@Injectable({ providedIn: 'root' })
export class TipoContratacaoResolve implements Resolve<ITipoContratacao> {
    constructor(private service: TipoContratacaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoContratacao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoContratacao>) => response.ok),
                map((tipoContratacao: HttpResponse<TipoContratacao>) => tipoContratacao.body)
            );
        }
        return of(new TipoContratacao());
    }
}

export const tipoContratacaoRoute: Routes = [
    {
        path: 'tipo-contratacao',
        component: TipoContratacaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoContratacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-contratacao/:id/view',
        component: TipoContratacaoDetailComponent,
        resolve: {
            tipoContratacao: TipoContratacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoContratacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-contratacao/new',
        component: TipoContratacaoUpdateComponent,
        resolve: {
            tipoContratacao: TipoContratacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoContratacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-contratacao/:id/edit',
        component: TipoContratacaoUpdateComponent,
        resolve: {
            tipoContratacao: TipoContratacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoContratacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoContratacaoPopupRoute: Routes = [
    {
        path: 'tipo-contratacao/:id/delete',
        component: TipoContratacaoDeletePopupComponent,
        resolve: {
            tipoContratacao: TipoContratacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoContratacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
