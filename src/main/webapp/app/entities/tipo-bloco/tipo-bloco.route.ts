import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoBloco } from 'app/shared/model/tipo-bloco.model';
import { TipoBlocoService } from './tipo-bloco.service';
import { TipoBlocoComponent } from './tipo-bloco.component';
import { TipoBlocoDetailComponent } from './tipo-bloco-detail.component';
import { TipoBlocoUpdateComponent } from './tipo-bloco-update.component';
import { TipoBlocoDeletePopupComponent } from './tipo-bloco-delete-dialog.component';
import { ITipoBloco } from 'app/shared/model/tipo-bloco.model';

@Injectable({ providedIn: 'root' })
export class TipoBlocoResolve implements Resolve<ITipoBloco> {
    constructor(private service: TipoBlocoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoBloco> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoBloco>) => response.ok),
                map((tipoBloco: HttpResponse<TipoBloco>) => tipoBloco.body)
            );
        }
        return of(new TipoBloco());
    }
}

export const tipoBlocoRoute: Routes = [
    {
        path: 'tipo-bloco',
        component: TipoBlocoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-bloco/:id/view',
        component: TipoBlocoDetailComponent,
        resolve: {
            tipoBloco: TipoBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-bloco/new',
        component: TipoBlocoUpdateComponent,
        resolve: {
            tipoBloco: TipoBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-bloco/:id/edit',
        component: TipoBlocoUpdateComponent,
        resolve: {
            tipoBloco: TipoBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoBlocoPopupRoute: Routes = [
    {
        path: 'tipo-bloco/:id/delete',
        component: TipoBlocoDeletePopupComponent,
        resolve: {
            tipoBloco: TipoBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoBloco.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
