import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';
import { RespAtivDescritivaService } from './resp-ativ-descritiva.service';
import { RespAtivDescritivaComponent } from './resp-ativ-descritiva.component';
import { RespAtivDescritivaDetailComponent } from './resp-ativ-descritiva-detail.component';
import { RespAtivDescritivaUpdateComponent } from './resp-ativ-descritiva-update.component';
import { RespAtivDescritivaDeletePopupComponent } from './resp-ativ-descritiva-delete-dialog.component';
import { IRespAtivDescritiva } from 'app/shared/model/resp-ativ-descritiva.model';

@Injectable({ providedIn: 'root' })
export class RespAtivDescritivaResolve implements Resolve<IRespAtivDescritiva> {
    constructor(private service: RespAtivDescritivaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespAtivDescritiva> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespAtivDescritiva>) => response.ok),
                map((respAtivDescritiva: HttpResponse<RespAtivDescritiva>) => respAtivDescritiva.body)
            );
        }
        return of(new RespAtivDescritiva());
    }
}

export const respAtivDescritivaRoute: Routes = [
    {
        path: 'resp-ativ-descritiva',
        component: RespAtivDescritivaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respAtivDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-ativ-descritiva/:id/view',
        component: RespAtivDescritivaDetailComponent,
        resolve: {
            respAtivDescritiva: RespAtivDescritivaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAtivDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-ativ-descritiva/new',
        component: RespAtivDescritivaUpdateComponent,
        resolve: {
            respAtivDescritiva: RespAtivDescritivaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAtivDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-ativ-descritiva/:id/edit',
        component: RespAtivDescritivaUpdateComponent,
        resolve: {
            respAtivDescritiva: RespAtivDescritivaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAtivDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respAtivDescritivaPopupRoute: Routes = [
    {
        path: 'resp-ativ-descritiva/:id/delete',
        component: RespAtivDescritivaDeletePopupComponent,
        resolve: {
            respAtivDescritiva: RespAtivDescritivaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAtivDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
