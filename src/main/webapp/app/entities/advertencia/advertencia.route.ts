import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Advertencia } from 'app/shared/model/advertencia.model';
import { AdvertenciaService } from './advertencia.service';
import { AdvertenciaComponent } from './advertencia.component';
import { AdvertenciaDetailComponent } from './advertencia-detail.component';
import { AdvertenciaUpdateComponent } from './advertencia-update.component';
import { AdvertenciaDeletePopupComponent } from './advertencia-delete-dialog.component';
import { IAdvertencia } from 'app/shared/model/advertencia.model';

@Injectable({ providedIn: 'root' })
export class AdvertenciaResolve implements Resolve<IAdvertencia> {
    constructor(private service: AdvertenciaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Advertencia> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Advertencia>) => response.ok),
                map((advertencia: HttpResponse<Advertencia>) => advertencia.body)
            );
        }
        return of(new Advertencia());
    }
}

export const advertenciaRoute: Routes = [
    {
        path: 'advertencia',
        component: AdvertenciaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.advertencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'advertencia/:id/view',
        component: AdvertenciaDetailComponent,
        resolve: {
            advertencia: AdvertenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.advertencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'advertencia/new',
        component: AdvertenciaUpdateComponent,
        resolve: {
            advertencia: AdvertenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.advertencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'advertencia/:id/edit',
        component: AdvertenciaUpdateComponent,
        resolve: {
            advertencia: AdvertenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.advertencia.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const advertenciaPopupRoute: Routes = [
    {
        path: 'advertencia/:id/delete',
        component: AdvertenciaDeletePopupComponent,
        resolve: {
            advertencia: AdvertenciaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.advertencia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
