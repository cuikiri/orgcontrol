import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FotoDocumento } from 'app/shared/model/foto-documento.model';
import { FotoDocumentoService } from './foto-documento.service';
import { FotoDocumentoComponent } from './foto-documento.component';
import { FotoDocumentoDetailComponent } from './foto-documento-detail.component';
import { FotoDocumentoUpdateComponent } from './foto-documento-update.component';
import { FotoDocumentoDeletePopupComponent } from './foto-documento-delete-dialog.component';
import { IFotoDocumento } from 'app/shared/model/foto-documento.model';

@Injectable({ providedIn: 'root' })
export class FotoDocumentoResolve implements Resolve<IFotoDocumento> {
    constructor(private service: FotoDocumentoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FotoDocumento> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FotoDocumento>) => response.ok),
                map((fotoDocumento: HttpResponse<FotoDocumento>) => fotoDocumento.body)
            );
        }
        return of(new FotoDocumento());
    }
}

export const fotoDocumentoRoute: Routes = [
    {
        path: 'foto-documento',
        component: FotoDocumentoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.fotoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'foto-documento/:id/view',
        component: FotoDocumentoDetailComponent,
        resolve: {
            fotoDocumento: FotoDocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fotoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'foto-documento/new',
        component: FotoDocumentoUpdateComponent,
        resolve: {
            fotoDocumento: FotoDocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fotoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'foto-documento/:id/edit',
        component: FotoDocumentoUpdateComponent,
        resolve: {
            fotoDocumento: FotoDocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fotoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fotoDocumentoPopupRoute: Routes = [
    {
        path: 'foto-documento/:id/delete',
        component: FotoDocumentoDeletePopupComponent,
        resolve: {
            fotoDocumento: FotoDocumentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fotoDocumento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
