import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Raca } from 'app/shared/model/raca.model';
import { RacaService } from './raca.service';
import { RacaComponent } from './raca.component';
import { RacaDetailComponent } from './raca-detail.component';
import { RacaUpdateComponent } from './raca-update.component';
import { RacaDeletePopupComponent } from './raca-delete-dialog.component';
import { IRaca } from 'app/shared/model/raca.model';

@Injectable({ providedIn: 'root' })
export class RacaResolve implements Resolve<IRaca> {
    constructor(private service: RacaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Raca> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Raca>) => response.ok),
                map((raca: HttpResponse<Raca>) => raca.body)
            );
        }
        return of(new Raca());
    }
}

export const racaRoute: Routes = [
    {
        path: 'raca',
        component: RacaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.raca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'raca/:id/view',
        component: RacaDetailComponent,
        resolve: {
            raca: RacaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.raca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'raca/new',
        component: RacaUpdateComponent,
        resolve: {
            raca: RacaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.raca.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'raca/:id/edit',
        component: RacaUpdateComponent,
        resolve: {
            raca: RacaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.raca.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const racaPopupRoute: Routes = [
    {
        path: 'raca/:id/delete',
        component: RacaDeletePopupComponent,
        resolve: {
            raca: RacaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.raca.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
