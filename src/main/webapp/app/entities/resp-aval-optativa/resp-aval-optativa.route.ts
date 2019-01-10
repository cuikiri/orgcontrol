import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';
import { RespAvalOptativaService } from './resp-aval-optativa.service';
import { RespAvalOptativaComponent } from './resp-aval-optativa.component';
import { RespAvalOptativaDetailComponent } from './resp-aval-optativa-detail.component';
import { RespAvalOptativaUpdateComponent } from './resp-aval-optativa-update.component';
import { RespAvalOptativaDeletePopupComponent } from './resp-aval-optativa-delete-dialog.component';
import { IRespAvalOptativa } from 'app/shared/model/resp-aval-optativa.model';

@Injectable({ providedIn: 'root' })
export class RespAvalOptativaResolve implements Resolve<IRespAvalOptativa> {
    constructor(private service: RespAvalOptativaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespAvalOptativa> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespAvalOptativa>) => response.ok),
                map((respAvalOptativa: HttpResponse<RespAvalOptativa>) => respAvalOptativa.body)
            );
        }
        return of(new RespAvalOptativa());
    }
}

export const respAvalOptativaRoute: Routes = [
    {
        path: 'resp-aval-optativa',
        component: RespAvalOptativaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-optativa/:id/view',
        component: RespAvalOptativaDetailComponent,
        resolve: {
            respAvalOptativa: RespAvalOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-optativa/new',
        component: RespAvalOptativaUpdateComponent,
        resolve: {
            respAvalOptativa: RespAvalOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-optativa/:id/edit',
        component: RespAvalOptativaUpdateComponent,
        resolve: {
            respAvalOptativa: RespAvalOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respAvalOptativaPopupRoute: Routes = [
    {
        path: 'resp-aval-optativa/:id/delete',
        component: RespAvalOptativaDeletePopupComponent,
        resolve: {
            respAvalOptativa: RespAvalOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
