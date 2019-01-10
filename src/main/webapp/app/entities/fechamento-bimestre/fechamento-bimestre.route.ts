import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';
import { FechamentoBimestreService } from './fechamento-bimestre.service';
import { FechamentoBimestreComponent } from './fechamento-bimestre.component';
import { FechamentoBimestreDetailComponent } from './fechamento-bimestre-detail.component';
import { FechamentoBimestreUpdateComponent } from './fechamento-bimestre-update.component';
import { FechamentoBimestreDeletePopupComponent } from './fechamento-bimestre-delete-dialog.component';
import { IFechamentoBimestre } from 'app/shared/model/fechamento-bimestre.model';

@Injectable({ providedIn: 'root' })
export class FechamentoBimestreResolve implements Resolve<IFechamentoBimestre> {
    constructor(private service: FechamentoBimestreService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FechamentoBimestre> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FechamentoBimestre>) => response.ok),
                map((fechamentoBimestre: HttpResponse<FechamentoBimestre>) => fechamentoBimestre.body)
            );
        }
        return of(new FechamentoBimestre());
    }
}

export const fechamentoBimestreRoute: Routes = [
    {
        path: 'fechamento-bimestre',
        component: FechamentoBimestreComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.fechamentoBimestre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fechamento-bimestre/:id/view',
        component: FechamentoBimestreDetailComponent,
        resolve: {
            fechamentoBimestre: FechamentoBimestreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fechamentoBimestre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fechamento-bimestre/new',
        component: FechamentoBimestreUpdateComponent,
        resolve: {
            fechamentoBimestre: FechamentoBimestreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fechamentoBimestre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'fechamento-bimestre/:id/edit',
        component: FechamentoBimestreUpdateComponent,
        resolve: {
            fechamentoBimestre: FechamentoBimestreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fechamentoBimestre.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const fechamentoBimestrePopupRoute: Routes = [
    {
        path: 'fechamento-bimestre/:id/delete',
        component: FechamentoBimestreDeletePopupComponent,
        resolve: {
            fechamentoBimestre: FechamentoBimestreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.fechamentoBimestre.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
