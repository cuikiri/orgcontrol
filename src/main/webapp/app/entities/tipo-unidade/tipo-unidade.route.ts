import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoUnidade } from 'app/shared/model/tipo-unidade.model';
import { TipoUnidadeService } from './tipo-unidade.service';
import { TipoUnidadeComponent } from './tipo-unidade.component';
import { TipoUnidadeDetailComponent } from './tipo-unidade-detail.component';
import { TipoUnidadeUpdateComponent } from './tipo-unidade-update.component';
import { TipoUnidadeDeletePopupComponent } from './tipo-unidade-delete-dialog.component';
import { ITipoUnidade } from 'app/shared/model/tipo-unidade.model';

@Injectable({ providedIn: 'root' })
export class TipoUnidadeResolve implements Resolve<ITipoUnidade> {
    constructor(private service: TipoUnidadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoUnidade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoUnidade>) => response.ok),
                map((tipoUnidade: HttpResponse<TipoUnidade>) => tipoUnidade.body)
            );
        }
        return of(new TipoUnidade());
    }
}

export const tipoUnidadeRoute: Routes = [
    {
        path: 'tipo-unidade',
        component: TipoUnidadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoUnidade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-unidade/:id/view',
        component: TipoUnidadeDetailComponent,
        resolve: {
            tipoUnidade: TipoUnidadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoUnidade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-unidade/new',
        component: TipoUnidadeUpdateComponent,
        resolve: {
            tipoUnidade: TipoUnidadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoUnidade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-unidade/:id/edit',
        component: TipoUnidadeUpdateComponent,
        resolve: {
            tipoUnidade: TipoUnidadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoUnidade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoUnidadePopupRoute: Routes = [
    {
        path: 'tipo-unidade/:id/delete',
        component: TipoUnidadeDeletePopupComponent,
        resolve: {
            tipoUnidade: TipoUnidadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoUnidade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
