import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { BimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';
import { BimestreAcompanhamentoService } from './bimestre-acompanhamento.service';
import { BimestreAcompanhamentoComponent } from './bimestre-acompanhamento.component';
import { BimestreAcompanhamentoDetailComponent } from './bimestre-acompanhamento-detail.component';
import { BimestreAcompanhamentoUpdateComponent } from './bimestre-acompanhamento-update.component';
import { BimestreAcompanhamentoDeletePopupComponent } from './bimestre-acompanhamento-delete-dialog.component';
import { IBimestreAcompanhamento } from 'app/shared/model/bimestre-acompanhamento.model';

@Injectable({ providedIn: 'root' })
export class BimestreAcompanhamentoResolve implements Resolve<IBimestreAcompanhamento> {
    constructor(private service: BimestreAcompanhamentoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<BimestreAcompanhamento> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<BimestreAcompanhamento>) => response.ok),
                map((bimestreAcompanhamento: HttpResponse<BimestreAcompanhamento>) => bimestreAcompanhamento.body)
            );
        }
        return of(new BimestreAcompanhamento());
    }
}

export const bimestreAcompanhamentoRoute: Routes = [
    {
        path: 'bimestre-acompanhamento',
        component: BimestreAcompanhamentoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.bimestreAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bimestre-acompanhamento/:id/view',
        component: BimestreAcompanhamentoDetailComponent,
        resolve: {
            bimestreAcompanhamento: BimestreAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bimestreAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bimestre-acompanhamento/new',
        component: BimestreAcompanhamentoUpdateComponent,
        resolve: {
            bimestreAcompanhamento: BimestreAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bimestreAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bimestre-acompanhamento/:id/edit',
        component: BimestreAcompanhamentoUpdateComponent,
        resolve: {
            bimestreAcompanhamento: BimestreAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bimestreAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bimestreAcompanhamentoPopupRoute: Routes = [
    {
        path: 'bimestre-acompanhamento/:id/delete',
        component: BimestreAcompanhamentoDeletePopupComponent,
        resolve: {
            bimestreAcompanhamento: BimestreAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.bimestreAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
