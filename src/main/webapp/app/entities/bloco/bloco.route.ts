import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Bloco } from 'app/shared/model/bloco.model';
import { BlocoService } from './bloco.service';
import { BlocoComponent } from './bloco.component';
import { BlocoDetailComponent } from './bloco-detail.component';
import { BlocoUpdateComponent } from './bloco-update.component';
import { BlocoDeletePopupComponent } from './bloco-delete-dialog.component';
import { IBloco } from 'app/shared/model/bloco.model';

@Injectable({ providedIn: 'root' })
export class BlocoResolve implements Resolve<IBloco> {
    constructor(private service: BlocoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Bloco> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Bloco>) => response.ok),
                map((bloco: HttpResponse<Bloco>) => bloco.body)
            );
        }
        return of(new Bloco());
    }
}

export const blocoRoute: Routes = [
    {
        path: 'bloco',
        component: BlocoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.bloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bloco/:id/view',
        component: BlocoDetailComponent,
        resolve: {
            bloco: BlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bloco/new',
        component: BlocoUpdateComponent,
        resolve: {
            bloco: BlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bloco/:id/edit',
        component: BlocoUpdateComponent,
        resolve: {
            bloco: BlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const blocoPopupRoute: Routes = [
    {
        path: 'bloco/:id/delete',
        component: BlocoDeletePopupComponent,
        resolve: {
            bloco: BlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bloco.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
