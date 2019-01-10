import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';
import { TipoAvaliacaoEconomicaService } from './tipo-avaliacao-economica.service';
import { TipoAvaliacaoEconomicaComponent } from './tipo-avaliacao-economica.component';
import { TipoAvaliacaoEconomicaDetailComponent } from './tipo-avaliacao-economica-detail.component';
import { TipoAvaliacaoEconomicaUpdateComponent } from './tipo-avaliacao-economica-update.component';
import { TipoAvaliacaoEconomicaDeletePopupComponent } from './tipo-avaliacao-economica-delete-dialog.component';
import { ITipoAvaliacaoEconomica } from 'app/shared/model/tipo-avaliacao-economica.model';

@Injectable({ providedIn: 'root' })
export class TipoAvaliacaoEconomicaResolve implements Resolve<ITipoAvaliacaoEconomica> {
    constructor(private service: TipoAvaliacaoEconomicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoAvaliacaoEconomica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoAvaliacaoEconomica>) => response.ok),
                map((tipoAvaliacaoEconomica: HttpResponse<TipoAvaliacaoEconomica>) => tipoAvaliacaoEconomica.body)
            );
        }
        return of(new TipoAvaliacaoEconomica());
    }
}

export const tipoAvaliacaoEconomicaRoute: Routes = [
    {
        path: 'tipo-avaliacao-economica',
        component: TipoAvaliacaoEconomicaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-avaliacao-economica/:id/view',
        component: TipoAvaliacaoEconomicaDetailComponent,
        resolve: {
            tipoAvaliacaoEconomica: TipoAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-avaliacao-economica/new',
        component: TipoAvaliacaoEconomicaUpdateComponent,
        resolve: {
            tipoAvaliacaoEconomica: TipoAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-avaliacao-economica/:id/edit',
        component: TipoAvaliacaoEconomicaUpdateComponent,
        resolve: {
            tipoAvaliacaoEconomica: TipoAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoAvaliacaoEconomicaPopupRoute: Routes = [
    {
        path: 'tipo-avaliacao-economica/:id/delete',
        component: TipoAvaliacaoEconomicaDeletePopupComponent,
        resolve: {
            tipoAvaliacaoEconomica: TipoAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
