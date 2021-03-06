import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Doenca } from 'app/shared/model/doenca.model';
import { DoencaService } from './doenca.service';
import { DoencaComponent } from './doenca.component';
import { DoencaDetailComponent } from './doenca-detail.component';
import { DoencaUpdateComponent } from './doenca-update.component';
import { DoencaDeletePopupComponent } from './doenca-delete-dialog.component';
import { IDoenca } from 'app/shared/model/doenca.model';

@Injectable({ providedIn: 'root' })
export class DoencaResolve implements Resolve<IDoenca> {
    constructor(private service: DoencaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Doenca> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Doenca>) => response.ok),
                map((doenca: HttpResponse<Doenca>) => doenca.body)
            );
        }
        return of(new Doenca());
    }
}

export const doencaRoute: Routes = [
    {
        path: 'doenca',
        component: DoencaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.doenca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'doenca/:id/view',
        component: DoencaDetailComponent,
        resolve: {
            doenca: DoencaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.doenca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'doenca/new',
        component: DoencaUpdateComponent,
        resolve: {
            doenca: DoencaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.doenca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'doenca/:id/edit',
        component: DoencaUpdateComponent,
        resolve: {
            doenca: DoencaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.doenca.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const doencaPopupRoute: Routes = [
    {
        path: 'doenca/:id/delete',
        component: DoencaDeletePopupComponent,
        resolve: {
            doenca: DoencaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.doenca.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
