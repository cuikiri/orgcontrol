import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';
import { TipoParteBlocoService } from './tipo-parte-bloco.service';
import { TipoParteBlocoComponent } from './tipo-parte-bloco.component';
import { TipoParteBlocoDetailComponent } from './tipo-parte-bloco-detail.component';
import { TipoParteBlocoUpdateComponent } from './tipo-parte-bloco-update.component';
import { TipoParteBlocoDeletePopupComponent } from './tipo-parte-bloco-delete-dialog.component';
import { ITipoParteBloco } from 'app/shared/model/tipo-parte-bloco.model';

@Injectable({ providedIn: 'root' })
export class TipoParteBlocoResolve implements Resolve<ITipoParteBloco> {
    constructor(private service: TipoParteBlocoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoParteBloco> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoParteBloco>) => response.ok),
                map((tipoParteBloco: HttpResponse<TipoParteBloco>) => tipoParteBloco.body)
            );
        }
        return of(new TipoParteBloco());
    }
}

export const tipoParteBlocoRoute: Routes = [
    {
        path: 'tipo-parte-bloco',
        component: TipoParteBlocoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoParteBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-parte-bloco/:id/view',
        component: TipoParteBlocoDetailComponent,
        resolve: {
            tipoParteBloco: TipoParteBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoParteBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-parte-bloco/new',
        component: TipoParteBlocoUpdateComponent,
        resolve: {
            tipoParteBloco: TipoParteBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoParteBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-parte-bloco/:id/edit',
        component: TipoParteBlocoUpdateComponent,
        resolve: {
            tipoParteBloco: TipoParteBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoParteBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoParteBlocoPopupRoute: Routes = [
    {
        path: 'tipo-parte-bloco/:id/delete',
        component: TipoParteBlocoDeletePopupComponent,
        resolve: {
            tipoParteBloco: TipoParteBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoParteBloco.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
