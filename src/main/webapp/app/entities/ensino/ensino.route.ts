import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Ensino } from 'app/shared/model/ensino.model';
import { EnsinoService } from './ensino.service';
import { EnsinoComponent } from './ensino.component';
import { EnsinoDetailComponent } from './ensino-detail.component';
import { EnsinoUpdateComponent } from './ensino-update.component';
import { EnsinoDeletePopupComponent } from './ensino-delete-dialog.component';
import { IEnsino } from 'app/shared/model/ensino.model';

@Injectable({ providedIn: 'root' })
export class EnsinoResolve implements Resolve<IEnsino> {
    constructor(private service: EnsinoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Ensino> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Ensino>) => response.ok),
                map((ensino: HttpResponse<Ensino>) => ensino.body)
            );
        }
        return of(new Ensino());
    }
}

export const ensinoRoute: Routes = [
    {
        path: 'ensino',
        component: EnsinoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.ensino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ensino/:id/view',
        component: EnsinoDetailComponent,
        resolve: {
            ensino: EnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.ensino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ensino/new',
        component: EnsinoUpdateComponent,
        resolve: {
            ensino: EnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.ensino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ensino/:id/edit',
        component: EnsinoUpdateComponent,
        resolve: {
            ensino: EnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.ensino.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ensinoPopupRoute: Routes = [
    {
        path: 'ensino/:id/delete',
        component: EnsinoDeletePopupComponent,
        resolve: {
            ensino: EnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.ensino.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
