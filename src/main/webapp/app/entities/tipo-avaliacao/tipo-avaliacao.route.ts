import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';
import { TipoAvaliacaoService } from './tipo-avaliacao.service';
import { TipoAvaliacaoComponent } from './tipo-avaliacao.component';
import { TipoAvaliacaoDetailComponent } from './tipo-avaliacao-detail.component';
import { TipoAvaliacaoUpdateComponent } from './tipo-avaliacao-update.component';
import { TipoAvaliacaoDeletePopupComponent } from './tipo-avaliacao-delete-dialog.component';
import { ITipoAvaliacao } from 'app/shared/model/tipo-avaliacao.model';

@Injectable({ providedIn: 'root' })
export class TipoAvaliacaoResolve implements Resolve<ITipoAvaliacao> {
    constructor(private service: TipoAvaliacaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoAvaliacao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoAvaliacao>) => response.ok),
                map((tipoAvaliacao: HttpResponse<TipoAvaliacao>) => tipoAvaliacao.body)
            );
        }
        return of(new TipoAvaliacao());
    }
}

export const tipoAvaliacaoRoute: Routes = [
    {
        path: 'tipo-avaliacao',
        component: TipoAvaliacaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-avaliacao/:id/view',
        component: TipoAvaliacaoDetailComponent,
        resolve: {
            tipoAvaliacao: TipoAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-avaliacao/new',
        component: TipoAvaliacaoUpdateComponent,
        resolve: {
            tipoAvaliacao: TipoAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-avaliacao/:id/edit',
        component: TipoAvaliacaoUpdateComponent,
        resolve: {
            tipoAvaliacao: TipoAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoAvaliacaoPopupRoute: Routes = [
    {
        path: 'tipo-avaliacao/:id/delete',
        component: TipoAvaliacaoDeletePopupComponent,
        resolve: {
            tipoAvaliacao: TipoAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
