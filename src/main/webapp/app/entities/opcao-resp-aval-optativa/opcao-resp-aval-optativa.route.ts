import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OpcaoRespAvalOptativa } from 'app/shared/model/opcao-resp-aval-optativa.model';
import { OpcaoRespAvalOptativaService } from './opcao-resp-aval-optativa.service';
import { OpcaoRespAvalOptativaComponent } from './opcao-resp-aval-optativa.component';
import { OpcaoRespAvalOptativaDetailComponent } from './opcao-resp-aval-optativa-detail.component';
import { OpcaoRespAvalOptativaUpdateComponent } from './opcao-resp-aval-optativa-update.component';
import { OpcaoRespAvalOptativaDeletePopupComponent } from './opcao-resp-aval-optativa-delete-dialog.component';
import { IOpcaoRespAvalOptativa } from 'app/shared/model/opcao-resp-aval-optativa.model';

@Injectable({ providedIn: 'root' })
export class OpcaoRespAvalOptativaResolve implements Resolve<IOpcaoRespAvalOptativa> {
    constructor(private service: OpcaoRespAvalOptativaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OpcaoRespAvalOptativa> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OpcaoRespAvalOptativa>) => response.ok),
                map((opcaoRespAvalOptativa: HttpResponse<OpcaoRespAvalOptativa>) => opcaoRespAvalOptativa.body)
            );
        }
        return of(new OpcaoRespAvalOptativa());
    }
}

export const opcaoRespAvalOptativaRoute: Routes = [
    {
        path: 'opcao-resp-aval-optativa',
        component: OpcaoRespAvalOptativaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-aval-optativa/:id/view',
        component: OpcaoRespAvalOptativaDetailComponent,
        resolve: {
            opcaoRespAvalOptativa: OpcaoRespAvalOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-aval-optativa/new',
        component: OpcaoRespAvalOptativaUpdateComponent,
        resolve: {
            opcaoRespAvalOptativa: OpcaoRespAvalOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-aval-optativa/:id/edit',
        component: OpcaoRespAvalOptativaUpdateComponent,
        resolve: {
            opcaoRespAvalOptativa: OpcaoRespAvalOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const opcaoRespAvalOptativaPopupRoute: Routes = [
    {
        path: 'opcao-resp-aval-optativa/:id/delete',
        component: OpcaoRespAvalOptativaDeletePopupComponent,
        resolve: {
            opcaoRespAvalOptativa: OpcaoRespAvalOptativaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAvalOptativa.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
