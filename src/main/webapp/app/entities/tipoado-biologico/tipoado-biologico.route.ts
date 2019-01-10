import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';
import { TipoadoBiologicoService } from './tipoado-biologico.service';
import { TipoadoBiologicoComponent } from './tipoado-biologico.component';
import { TipoadoBiologicoDetailComponent } from './tipoado-biologico-detail.component';
import { TipoadoBiologicoUpdateComponent } from './tipoado-biologico-update.component';
import { TipoadoBiologicoDeletePopupComponent } from './tipoado-biologico-delete-dialog.component';
import { ITipoadoBiologico } from 'app/shared/model/tipoado-biologico.model';

@Injectable({ providedIn: 'root' })
export class TipoadoBiologicoResolve implements Resolve<ITipoadoBiologico> {
    constructor(private service: TipoadoBiologicoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoadoBiologico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoadoBiologico>) => response.ok),
                map((tipoadoBiologico: HttpResponse<TipoadoBiologico>) => tipoadoBiologico.body)
            );
        }
        return of(new TipoadoBiologico());
    }
}

export const tipoadoBiologicoRoute: Routes = [
    {
        path: 'tipoado-biologico',
        component: TipoadoBiologicoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipoado-biologico/:id/view',
        component: TipoadoBiologicoDetailComponent,
        resolve: {
            tipoadoBiologico: TipoadoBiologicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipoado-biologico/new',
        component: TipoadoBiologicoUpdateComponent,
        resolve: {
            tipoadoBiologico: TipoadoBiologicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipoado-biologico/:id/edit',
        component: TipoadoBiologicoUpdateComponent,
        resolve: {
            tipoadoBiologico: TipoadoBiologicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoadoBiologicoPopupRoute: Routes = [
    {
        path: 'tipoado-biologico/:id/delete',
        component: TipoadoBiologicoDeletePopupComponent,
        resolve: {
            tipoadoBiologico: TipoadoBiologicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
