import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Chapa } from 'app/shared/model/chapa.model';
import { ChapaService } from './chapa.service';
import { ChapaComponent } from './chapa.component';
import { ChapaDetailComponent } from './chapa-detail.component';
import { ChapaUpdateComponent } from './chapa-update.component';
import { ChapaDeletePopupComponent } from './chapa-delete-dialog.component';
import { IChapa } from 'app/shared/model/chapa.model';

@Injectable({ providedIn: 'root' })
export class ChapaResolve implements Resolve<IChapa> {
    constructor(private service: ChapaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Chapa> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Chapa>) => response.ok),
                map((chapa: HttpResponse<Chapa>) => chapa.body)
            );
        }
        return of(new Chapa());
    }
}

export const chapaRoute: Routes = [
    {
        path: 'chapa',
        component: ChapaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.chapa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'chapa/:id/view',
        component: ChapaDetailComponent,
        resolve: {
            chapa: ChapaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.chapa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'chapa/new',
        component: ChapaUpdateComponent,
        resolve: {
            chapa: ChapaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.chapa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'chapa/:id/edit',
        component: ChapaUpdateComponent,
        resolve: {
            chapa: ChapaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.chapa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const chapaPopupRoute: Routes = [
    {
        path: 'chapa/:id/delete',
        component: ChapaDeletePopupComponent,
        resolve: {
            chapa: ChapaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.chapa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
