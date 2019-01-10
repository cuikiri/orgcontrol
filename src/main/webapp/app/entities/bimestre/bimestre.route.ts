import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Bimestre } from 'app/shared/model/bimestre.model';
import { BimestreService } from './bimestre.service';
import { BimestreComponent } from './bimestre.component';
import { BimestreDetailComponent } from './bimestre-detail.component';
import { BimestreUpdateComponent } from './bimestre-update.component';
import { BimestreDeletePopupComponent } from './bimestre-delete-dialog.component';
import { IBimestre } from 'app/shared/model/bimestre.model';

@Injectable({ providedIn: 'root' })
export class BimestreResolve implements Resolve<IBimestre> {
    constructor(private service: BimestreService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Bimestre> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Bimestre>) => response.ok),
                map((bimestre: HttpResponse<Bimestre>) => bimestre.body)
            );
        }
        return of(new Bimestre());
    }
}

export const bimestreRoute: Routes = [
    {
        path: 'bimestre',
        component: BimestreComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.bimestre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bimestre/:id/view',
        component: BimestreDetailComponent,
        resolve: {
            bimestre: BimestreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bimestre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bimestre/new',
        component: BimestreUpdateComponent,
        resolve: {
            bimestre: BimestreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bimestre.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bimestre/:id/edit',
        component: BimestreUpdateComponent,
        resolve: {
            bimestre: BimestreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bimestre.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bimestrePopupRoute: Routes = [
    {
        path: 'bimestre/:id/delete',
        component: BimestreDeletePopupComponent,
        resolve: {
            bimestre: BimestreResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bimestre.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
