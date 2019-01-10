import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';
import { RespAvalDescritivaEconomicaService } from './resp-aval-descritiva-economica.service';
import { RespAvalDescritivaEconomicaComponent } from './resp-aval-descritiva-economica.component';
import { RespAvalDescritivaEconomicaDetailComponent } from './resp-aval-descritiva-economica-detail.component';
import { RespAvalDescritivaEconomicaUpdateComponent } from './resp-aval-descritiva-economica-update.component';
import { RespAvalDescritivaEconomicaDeletePopupComponent } from './resp-aval-descritiva-economica-delete-dialog.component';
import { IRespAvalDescritivaEconomica } from 'app/shared/model/resp-aval-descritiva-economica.model';

@Injectable({ providedIn: 'root' })
export class RespAvalDescritivaEconomicaResolve implements Resolve<IRespAvalDescritivaEconomica> {
    constructor(private service: RespAvalDescritivaEconomicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespAvalDescritivaEconomica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespAvalDescritivaEconomica>) => response.ok),
                map((respAvalDescritivaEconomica: HttpResponse<RespAvalDescritivaEconomica>) => respAvalDescritivaEconomica.body)
            );
        }
        return of(new RespAvalDescritivaEconomica());
    }
}

export const respAvalDescritivaEconomicaRoute: Routes = [
    {
        path: 'resp-aval-descritiva-economica',
        component: RespAvalDescritivaEconomicaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respAvalDescritivaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-descritiva-economica/:id/view',
        component: RespAvalDescritivaEconomicaDetailComponent,
        resolve: {
            respAvalDescritivaEconomica: RespAvalDescritivaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalDescritivaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-descritiva-economica/new',
        component: RespAvalDescritivaEconomicaUpdateComponent,
        resolve: {
            respAvalDescritivaEconomica: RespAvalDescritivaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalDescritivaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resp-aval-descritiva-economica/:id/edit',
        component: RespAvalDescritivaEconomicaUpdateComponent,
        resolve: {
            respAvalDescritivaEconomica: RespAvalDescritivaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalDescritivaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respAvalDescritivaEconomicaPopupRoute: Routes = [
    {
        path: 'resp-aval-descritiva-economica/:id/delete',
        component: RespAvalDescritivaEconomicaDeletePopupComponent,
        resolve: {
            respAvalDescritivaEconomica: RespAvalDescritivaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respAvalDescritivaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
