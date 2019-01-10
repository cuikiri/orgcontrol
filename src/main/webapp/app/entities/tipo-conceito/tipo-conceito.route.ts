import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoConceito } from 'app/shared/model/tipo-conceito.model';
import { TipoConceitoService } from './tipo-conceito.service';
import { TipoConceitoComponent } from './tipo-conceito.component';
import { TipoConceitoDetailComponent } from './tipo-conceito-detail.component';
import { TipoConceitoUpdateComponent } from './tipo-conceito-update.component';
import { TipoConceitoDeletePopupComponent } from './tipo-conceito-delete-dialog.component';
import { ITipoConceito } from 'app/shared/model/tipo-conceito.model';

@Injectable({ providedIn: 'root' })
export class TipoConceitoResolve implements Resolve<ITipoConceito> {
    constructor(private service: TipoConceitoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoConceito> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoConceito>) => response.ok),
                map((tipoConceito: HttpResponse<TipoConceito>) => tipoConceito.body)
            );
        }
        return of(new TipoConceito());
    }
}

export const tipoConceitoRoute: Routes = [
    {
        path: 'tipo-conceito',
        component: TipoConceitoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoConceito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-conceito/:id/view',
        component: TipoConceitoDetailComponent,
        resolve: {
            tipoConceito: TipoConceitoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoConceito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-conceito/new',
        component: TipoConceitoUpdateComponent,
        resolve: {
            tipoConceito: TipoConceitoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoConceito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-conceito/:id/edit',
        component: TipoConceitoUpdateComponent,
        resolve: {
            tipoConceito: TipoConceitoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoConceito.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoConceitoPopupRoute: Routes = [
    {
        path: 'tipo-conceito/:id/delete',
        component: TipoConceitoDeletePopupComponent,
        resolve: {
            tipoConceito: TipoConceitoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoConceito.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
