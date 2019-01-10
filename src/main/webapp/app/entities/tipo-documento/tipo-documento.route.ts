import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoDocumento } from 'app/shared/model/tipo-documento.model';
import { TipoDocumentoService } from './tipo-documento.service';
import { TipoDocumentoComponent } from './tipo-documento.component';
import { TipoDocumentoDetailComponent } from './tipo-documento-detail.component';
import { TipoDocumentoUpdateComponent } from './tipo-documento-update.component';
import { TipoDocumentoDeletePopupComponent } from './tipo-documento-delete-dialog.component';
import { ITipoDocumento } from 'app/shared/model/tipo-documento.model';

@Injectable({ providedIn: 'root' })
export class TipoDocumentoResolve implements Resolve<ITipoDocumento> {
    constructor(private service: TipoDocumentoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoDocumento> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoDocumento>) => response.ok),
                map((tipoDocumento: HttpResponse<TipoDocumento>) => tipoDocumento.body)
            );
        }
        return of(new TipoDocumento());
    }
}

export const tipoDocumentoRoute: Routes = [
    {
        path: 'tipo-documento',
        component: TipoDocumentoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-documento/:id/view',
        component: TipoDocumentoDetailComponent,
        resolve: {
            tipoDocumento: TipoDocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-documento/new',
        component: TipoDocumentoUpdateComponent,
        resolve: {
            tipoDocumento: TipoDocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-documento/:id/edit',
        component: TipoDocumentoUpdateComponent,
        resolve: {
            tipoDocumento: TipoDocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoDocumentoPopupRoute: Routes = [
    {
        path: 'tipo-documento/:id/delete',
        component: TipoDocumentoDeletePopupComponent,
        resolve: {
            tipoDocumento: TipoDocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
