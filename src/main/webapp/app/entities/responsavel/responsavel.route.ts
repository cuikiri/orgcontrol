import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Responsavel } from 'app/shared/model/responsavel.model';
import { ResponsavelService } from './responsavel.service';
import { ResponsavelComponent } from './responsavel.component';
import { ResponsavelDetailComponent } from './responsavel-detail.component';
import { ResponsavelUpdateComponent } from './responsavel-update.component';
import { ResponsavelDeletePopupComponent } from './responsavel-delete-dialog.component';
import { IResponsavel } from 'app/shared/model/responsavel.model';

@Injectable({ providedIn: 'root' })
export class ResponsavelResolve implements Resolve<IResponsavel> {
    constructor(private service: ResponsavelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Responsavel> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Responsavel>) => response.ok),
                map((responsavel: HttpResponse<Responsavel>) => responsavel.body)
            );
        }
        return of(new Responsavel());
    }
}

export const responsavelRoute: Routes = [
    {
        path: 'responsavel',
        component: ResponsavelComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.responsavel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'responsavel/:id/view',
        component: ResponsavelDetailComponent,
        resolve: {
            responsavel: ResponsavelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.responsavel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'responsavel/new',
        component: ResponsavelUpdateComponent,
        resolve: {
            responsavel: ResponsavelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.responsavel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'responsavel/:id/edit',
        component: ResponsavelUpdateComponent,
        resolve: {
            responsavel: ResponsavelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.responsavel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const responsavelPopupRoute: Routes = [
    {
        path: 'responsavel/:id/delete',
        component: ResponsavelDeletePopupComponent,
        resolve: {
            responsavel: ResponsavelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.responsavel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
