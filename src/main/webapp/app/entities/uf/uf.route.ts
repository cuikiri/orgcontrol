import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Uf } from 'app/shared/model/uf.model';
import { UfService } from './uf.service';
import { UfComponent } from './uf.component';
import { UfDetailComponent } from './uf-detail.component';
import { UfUpdateComponent } from './uf-update.component';
import { UfDeletePopupComponent } from './uf-delete-dialog.component';
import { IUf } from 'app/shared/model/uf.model';

@Injectable({ providedIn: 'root' })
export class UfResolve implements Resolve<IUf> {
    constructor(private service: UfService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Uf> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Uf>) => response.ok),
                map((uf: HttpResponse<Uf>) => uf.body)
            );
        }
        return of(new Uf());
    }
}

export const ufRoute: Routes = [
    {
        path: 'uf',
        component: UfComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.uf.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uf/:id/view',
        component: UfDetailComponent,
        resolve: {
            uf: UfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.uf.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uf/new',
        component: UfUpdateComponent,
        resolve: {
            uf: UfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.uf.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'uf/:id/edit',
        component: UfUpdateComponent,
        resolve: {
            uf: UfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.uf.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ufPopupRoute: Routes = [
    {
        path: 'uf/:id/delete',
        component: UfDeletePopupComponent,
        resolve: {
            uf: UfResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.uf.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
