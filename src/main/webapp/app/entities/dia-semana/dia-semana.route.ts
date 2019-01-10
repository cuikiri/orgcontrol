import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DiaSemana } from 'app/shared/model/dia-semana.model';
import { DiaSemanaService } from './dia-semana.service';
import { DiaSemanaComponent } from './dia-semana.component';
import { DiaSemanaDetailComponent } from './dia-semana-detail.component';
import { DiaSemanaUpdateComponent } from './dia-semana-update.component';
import { DiaSemanaDeletePopupComponent } from './dia-semana-delete-dialog.component';
import { IDiaSemana } from 'app/shared/model/dia-semana.model';

@Injectable({ providedIn: 'root' })
export class DiaSemanaResolve implements Resolve<IDiaSemana> {
    constructor(private service: DiaSemanaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DiaSemana> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DiaSemana>) => response.ok),
                map((diaSemana: HttpResponse<DiaSemana>) => diaSemana.body)
            );
        }
        return of(new DiaSemana());
    }
}

export const diaSemanaRoute: Routes = [
    {
        path: 'dia-semana',
        component: DiaSemanaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.diaSemana.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dia-semana/:id/view',
        component: DiaSemanaDetailComponent,
        resolve: {
            diaSemana: DiaSemanaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diaSemana.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dia-semana/new',
        component: DiaSemanaUpdateComponent,
        resolve: {
            diaSemana: DiaSemanaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diaSemana.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dia-semana/:id/edit',
        component: DiaSemanaUpdateComponent,
        resolve: {
            diaSemana: DiaSemanaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diaSemana.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const diaSemanaPopupRoute: Routes = [
    {
        path: 'dia-semana/:id/delete',
        component: DiaSemanaDeletePopupComponent,
        resolve: {
            diaSemana: DiaSemanaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diaSemana.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
