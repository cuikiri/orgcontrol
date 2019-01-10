import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PeriodoSemana } from 'app/shared/model/periodo-semana.model';
import { PeriodoSemanaService } from './periodo-semana.service';
import { PeriodoSemanaComponent } from './periodo-semana.component';
import { PeriodoSemanaDetailComponent } from './periodo-semana-detail.component';
import { PeriodoSemanaUpdateComponent } from './periodo-semana-update.component';
import { PeriodoSemanaDeletePopupComponent } from './periodo-semana-delete-dialog.component';
import { IPeriodoSemana } from 'app/shared/model/periodo-semana.model';

@Injectable({ providedIn: 'root' })
export class PeriodoSemanaResolve implements Resolve<IPeriodoSemana> {
    constructor(private service: PeriodoSemanaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PeriodoSemana> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PeriodoSemana>) => response.ok),
                map((periodoSemana: HttpResponse<PeriodoSemana>) => periodoSemana.body)
            );
        }
        return of(new PeriodoSemana());
    }
}

export const periodoSemanaRoute: Routes = [
    {
        path: 'periodo-semana',
        component: PeriodoSemanaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.periodoSemana.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-semana/:id/view',
        component: PeriodoSemanaDetailComponent,
        resolve: {
            periodoSemana: PeriodoSemanaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoSemana.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-semana/new',
        component: PeriodoSemanaUpdateComponent,
        resolve: {
            periodoSemana: PeriodoSemanaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoSemana.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-semana/:id/edit',
        component: PeriodoSemanaUpdateComponent,
        resolve: {
            periodoSemana: PeriodoSemanaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoSemana.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const periodoSemanaPopupRoute: Routes = [
    {
        path: 'periodo-semana/:id/delete',
        component: PeriodoSemanaDeletePopupComponent,
        resolve: {
            periodoSemana: PeriodoSemanaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoSemana.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
