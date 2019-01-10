import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Conceito } from 'app/shared/model/conceito.model';
import { ConceitoService } from './conceito.service';
import { ConceitoComponent } from './conceito.component';
import { ConceitoDetailComponent } from './conceito-detail.component';
import { ConceitoUpdateComponent } from './conceito-update.component';
import { ConceitoDeletePopupComponent } from './conceito-delete-dialog.component';
import { IConceito } from 'app/shared/model/conceito.model';

@Injectable({ providedIn: 'root' })
export class ConceitoResolve implements Resolve<IConceito> {
    constructor(private service: ConceitoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Conceito> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Conceito>) => response.ok),
                map((conceito: HttpResponse<Conceito>) => conceito.body)
            );
        }
        return of(new Conceito());
    }
}

export const conceitoRoute: Routes = [
    {
        path: 'conceito',
        component: ConceitoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.conceito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conceito/:id/view',
        component: ConceitoDetailComponent,
        resolve: {
            conceito: ConceitoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conceito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conceito/new',
        component: ConceitoUpdateComponent,
        resolve: {
            conceito: ConceitoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conceito.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conceito/:id/edit',
        component: ConceitoUpdateComponent,
        resolve: {
            conceito: ConceitoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conceito.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const conceitoPopupRoute: Routes = [
    {
        path: 'conceito/:id/delete',
        component: ConceitoDeletePopupComponent,
        resolve: {
            conceito: ConceitoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conceito.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
