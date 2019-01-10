import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';
import { RespAvalOptativaEconomicaService } from './resp-aval-optativa-economica.service';
import { RespAvalOptativaEconomicaComponent } from './resp-aval-optativa-economica.component';
import { RespAvalOptativaEconomicaDetailComponent } from './resp-aval-optativa-economica-detail.component';
import { RespAvalOptativaEconomicaUpdateComponent } from './resp-aval-optativa-economica-update.component';
import { RespAvalOptativaEconomicaDeletePopupComponent } from './resp-aval-optativa-economica-delete-dialog.component';
import { IRespAvalOptativaEconomica } from 'app/shared/model/resp-aval-optativa-economica.model';

@Injectable({ providedIn: 'root' })
export class RespAvalOptativaEconomicaResolve implements Resolve<IRespAvalOptativaEconomica> {
    constructor(private service: RespAvalOptativaEconomicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespAvalOptativaEconomica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespAvalOptativaEconomica>) => response.ok),
                map((respAvalOptativaEconomica: HttpResponse<RespAvalOptativaEconomica>) => respAvalOptativaEconomica.body)
            );
        }
        return of(new RespAvalOptativaEconomica());
    }
}

export const respAvalOptativaEconomicaRoute: Routes = [
    {
        path: 'resp-aval-optativa-economica',
        component: RespAvalOptativaEconomicaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-optativa-economica/:id/view',
        component: RespAvalOptativaEconomicaDetailComponent,
        resolve: {
            respAvalOptativaEconomica: RespAvalOptativaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-optativa-economica/new',
        component: RespAvalOptativaEconomicaUpdateComponent,
        resolve: {
            respAvalOptativaEconomica: RespAvalOptativaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-optativa-economica/:id/edit',
        component: RespAvalOptativaEconomicaUpdateComponent,
        resolve: {
            respAvalOptativaEconomica: RespAvalOptativaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respAvalOptativaEconomicaPopupRoute: Routes = [
    {
        path: 'resp-aval-optativa-economica/:id/delete',
        component: RespAvalOptativaEconomicaDeletePopupComponent,
        resolve: {
            respAvalOptativaEconomica: RespAvalOptativaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
