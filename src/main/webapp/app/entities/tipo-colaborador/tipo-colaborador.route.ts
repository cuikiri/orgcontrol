import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoColaborador } from 'app/shared/model/tipo-colaborador.model';
import { TipoColaboradorService } from './tipo-colaborador.service';
import { TipoColaboradorComponent } from './tipo-colaborador.component';
import { TipoColaboradorDetailComponent } from './tipo-colaborador-detail.component';
import { TipoColaboradorUpdateComponent } from './tipo-colaborador-update.component';
import { TipoColaboradorDeletePopupComponent } from './tipo-colaborador-delete-dialog.component';
import { ITipoColaborador } from 'app/shared/model/tipo-colaborador.model';

@Injectable({ providedIn: 'root' })
export class TipoColaboradorResolve implements Resolve<ITipoColaborador> {
    constructor(private service: TipoColaboradorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoColaborador> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoColaborador>) => response.ok),
                map((tipoColaborador: HttpResponse<TipoColaborador>) => tipoColaborador.body)
            );
        }
        return of(new TipoColaborador());
    }
}

export const tipoColaboradorRoute: Routes = [
    {
        path: 'tipo-colaborador',
        component: TipoColaboradorComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoColaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-colaborador/:id/view',
        component: TipoColaboradorDetailComponent,
        resolve: {
            tipoColaborador: TipoColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoColaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-colaborador/new',
        component: TipoColaboradorUpdateComponent,
        resolve: {
            tipoColaborador: TipoColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoColaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-colaborador/:id/edit',
        component: TipoColaboradorUpdateComponent,
        resolve: {
            tipoColaborador: TipoColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoColaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoColaboradorPopupRoute: Routes = [
    {
        path: 'tipo-colaborador/:id/delete',
        component: TipoColaboradorDeletePopupComponent,
        resolve: {
            tipoColaborador: TipoColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoColaborador.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
