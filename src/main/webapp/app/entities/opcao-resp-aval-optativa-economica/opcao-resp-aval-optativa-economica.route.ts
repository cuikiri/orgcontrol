import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OpcaoRespAvalOptativaEconomica } from 'app/shared/model/opcao-resp-aval-optativa-economica.model';
import { OpcaoRespAvalOptativaEconomicaService } from './opcao-resp-aval-optativa-economica.service';
import { OpcaoRespAvalOptativaEconomicaComponent } from './opcao-resp-aval-optativa-economica.component';
import { OpcaoRespAvalOptativaEconomicaDetailComponent } from './opcao-resp-aval-optativa-economica-detail.component';
import { OpcaoRespAvalOptativaEconomicaUpdateComponent } from './opcao-resp-aval-optativa-economica-update.component';
import { OpcaoRespAvalOptativaEconomicaDeletePopupComponent } from './opcao-resp-aval-optativa-economica-delete-dialog.component';
import { IOpcaoRespAvalOptativaEconomica } from 'app/shared/model/opcao-resp-aval-optativa-economica.model';

@Injectable({ providedIn: 'root' })
export class OpcaoRespAvalOptativaEconomicaResolve implements Resolve<IOpcaoRespAvalOptativaEconomica> {
    constructor(private service: OpcaoRespAvalOptativaEconomicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OpcaoRespAvalOptativaEconomica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OpcaoRespAvalOptativaEconomica>) => response.ok),
                map((opcaoRespAvalOptativaEconomica: HttpResponse<OpcaoRespAvalOptativaEconomica>) => opcaoRespAvalOptativaEconomica.body)
            );
        }
        return of(new OpcaoRespAvalOptativaEconomica());
    }
}

export const opcaoRespAvalOptativaEconomicaRoute: Routes = [
    {
        path: 'opcao-resp-aval-optativa-economica',
        component: OpcaoRespAvalOptativaEconomicaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-aval-optativa-economica/:id/view',
        component: OpcaoRespAvalOptativaEconomicaDetailComponent,
        resolve: {
            opcaoRespAvalOptativaEconomica: OpcaoRespAvalOptativaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-aval-optativa-economica/new',
        component: OpcaoRespAvalOptativaEconomicaUpdateComponent,
        resolve: {
            opcaoRespAvalOptativaEconomica: OpcaoRespAvalOptativaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-aval-optativa-economica/:id/edit',
        component: OpcaoRespAvalOptativaEconomicaUpdateComponent,
        resolve: {
            opcaoRespAvalOptativaEconomica: OpcaoRespAvalOptativaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const opcaoRespAvalOptativaEconomicaPopupRoute: Routes = [
    {
        path: 'opcao-resp-aval-optativa-economica/:id/delete',
        component: OpcaoRespAvalOptativaEconomicaDeletePopupComponent,
        resolve: {
            opcaoRespAvalOptativaEconomica: OpcaoRespAvalOptativaEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativaEconomica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
