import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';
import { RespAtivOptativaService } from './resp-ativ-optativa.service';
import { RespAtivOptativaComponent } from './resp-ativ-optativa.component';
import { RespAtivOptativaDetailComponent } from './resp-ativ-optativa-detail.component';
import { RespAtivOptativaUpdateComponent } from './resp-ativ-optativa-update.component';
import { RespAtivOptativaDeletePopupComponent } from './resp-ativ-optativa-delete-dialog.component';
import { IRespAtivOptativa } from 'app/shared/model/resp-ativ-optativa.model';

@Injectable({ providedIn: 'root' })
export class RespAtivOptativaResolve implements Resolve<IRespAtivOptativa> {
    constructor(private service: RespAtivOptativaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespAtivOptativa> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespAtivOptativa>) => response.ok),
                map((respAtivOptativa: HttpResponse<RespAtivOptativa>) => respAtivOptativa.body)
            );
        }
        return of(new RespAtivOptativa());
    }
}

export const respAtivOptativaRoute: Routes = [
    {
        path: 'resp-ativ-optativa',
        component: RespAtivOptativaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respAtivOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-ativ-optativa/:id/view',
        component: RespAtivOptativaDetailComponent,
        resolve: {
            respAtivOptativa: RespAtivOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAtivOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-ativ-optativa/new',
        component: RespAtivOptativaUpdateComponent,
        resolve: {
            respAtivOptativa: RespAtivOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAtivOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-ativ-optativa/:id/edit',
        component: RespAtivOptativaUpdateComponent,
        resolve: {
            respAtivOptativa: RespAtivOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAtivOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respAtivOptativaPopupRoute: Routes = [
    {
        path: 'resp-ativ-optativa/:id/delete',
        component: RespAtivOptativaDeletePopupComponent,
        resolve: {
            respAtivOptativa: RespAtivOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAtivOptativa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
