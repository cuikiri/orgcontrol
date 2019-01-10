import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';
import { RespAvalDescritivaService } from './resp-aval-descritiva.service';
import { RespAvalDescritivaComponent } from './resp-aval-descritiva.component';
import { RespAvalDescritivaDetailComponent } from './resp-aval-descritiva-detail.component';
import { RespAvalDescritivaUpdateComponent } from './resp-aval-descritiva-update.component';
import { RespAvalDescritivaDeletePopupComponent } from './resp-aval-descritiva-delete-dialog.component';
import { IRespAvalDescritiva } from 'app/shared/model/resp-aval-descritiva.model';

@Injectable({ providedIn: 'root' })
export class RespAvalDescritivaResolve implements Resolve<IRespAvalDescritiva> {
    constructor(private service: RespAvalDescritivaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespAvalDescritiva> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespAvalDescritiva>) => response.ok),
                map((respAvalDescritiva: HttpResponse<RespAvalDescritiva>) => respAvalDescritiva.body)
            );
        }
        return of(new RespAvalDescritiva());
    }
}

export const respAvalDescritivaRoute: Routes = [
    {
        path: 'resp-aval-descritiva',
        component: RespAvalDescritivaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respAvalDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-descritiva/:id/view',
        component: RespAvalDescritivaDetailComponent,
        resolve: {
            respAvalDescritiva: RespAvalDescritivaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-descritiva/new',
        component: RespAvalDescritivaUpdateComponent,
        resolve: {
            respAvalDescritiva: RespAvalDescritivaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-descritiva/:id/edit',
        component: RespAvalDescritivaUpdateComponent,
        resolve: {
            respAvalDescritiva: RespAvalDescritivaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respAvalDescritivaPopupRoute: Routes = [
    {
        path: 'resp-aval-descritiva/:id/delete',
        component: RespAvalDescritivaDeletePopupComponent,
        resolve: {
            respAvalDescritiva: RespAvalDescritivaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalDescritiva.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
