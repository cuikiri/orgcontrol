import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CaracteristicasPsiquicas } from 'app/shared/model/caracteristicas-psiquicas.model';
import { CaracteristicasPsiquicasService } from './caracteristicas-psiquicas.service';
import { CaracteristicasPsiquicasComponent } from './caracteristicas-psiquicas.component';
import { CaracteristicasPsiquicasDetailComponent } from './caracteristicas-psiquicas-detail.component';
import { CaracteristicasPsiquicasUpdateComponent } from './caracteristicas-psiquicas-update.component';
import { CaracteristicasPsiquicasDeletePopupComponent } from './caracteristicas-psiquicas-delete-dialog.component';
import { ICaracteristicasPsiquicas } from 'app/shared/model/caracteristicas-psiquicas.model';

@Injectable({ providedIn: 'root' })
export class CaracteristicasPsiquicasResolve implements Resolve<ICaracteristicasPsiquicas> {
    constructor(private service: CaracteristicasPsiquicasService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CaracteristicasPsiquicas> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CaracteristicasPsiquicas>) => response.ok),
                map((caracteristicasPsiquicas: HttpResponse<CaracteristicasPsiquicas>) => caracteristicasPsiquicas.body)
            );
        }
        return of(new CaracteristicasPsiquicas());
    }
}

export const caracteristicasPsiquicasRoute: Routes = [
    {
        path: 'caracteristicas-psiquicas',
        component: CaracteristicasPsiquicasComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.caracteristicasPsiquicas.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'caracteristicas-psiquicas/:id/view',
        component: CaracteristicasPsiquicasDetailComponent,
        resolve: {
            caracteristicasPsiquicas: CaracteristicasPsiquicasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.caracteristicasPsiquicas.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'caracteristicas-psiquicas/new',
        component: CaracteristicasPsiquicasUpdateComponent,
        resolve: {
            caracteristicasPsiquicas: CaracteristicasPsiquicasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.caracteristicasPsiquicas.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'caracteristicas-psiquicas/:id/edit',
        component: CaracteristicasPsiquicasUpdateComponent,
        resolve: {
            caracteristicasPsiquicas: CaracteristicasPsiquicasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.caracteristicasPsiquicas.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const caracteristicasPsiquicasPopupRoute: Routes = [
    {
        path: 'caracteristicas-psiquicas/:id/delete',
        component: CaracteristicasPsiquicasDeletePopupComponent,
        resolve: {
            caracteristicasPsiquicas: CaracteristicasPsiquicasResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.caracteristicasPsiquicas.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
