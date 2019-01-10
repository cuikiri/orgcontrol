import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PeriodoAtividade } from 'app/shared/model/periodo-atividade.model';
import { PeriodoAtividadeService } from './periodo-atividade.service';
import { PeriodoAtividadeComponent } from './periodo-atividade.component';
import { PeriodoAtividadeDetailComponent } from './periodo-atividade-detail.component';
import { PeriodoAtividadeUpdateComponent } from './periodo-atividade-update.component';
import { PeriodoAtividadeDeletePopupComponent } from './periodo-atividade-delete-dialog.component';
import { IPeriodoAtividade } from 'app/shared/model/periodo-atividade.model';

@Injectable({ providedIn: 'root' })
export class PeriodoAtividadeResolve implements Resolve<IPeriodoAtividade> {
    constructor(private service: PeriodoAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PeriodoAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PeriodoAtividade>) => response.ok),
                map((periodoAtividade: HttpResponse<PeriodoAtividade>) => periodoAtividade.body)
            );
        }
        return of(new PeriodoAtividade());
    }
}

export const periodoAtividadeRoute: Routes = [
    {
        path: 'periodo-atividade',
        component: PeriodoAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.periodoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-atividade/:id/view',
        component: PeriodoAtividadeDetailComponent,
        resolve: {
            periodoAtividade: PeriodoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-atividade/new',
        component: PeriodoAtividadeUpdateComponent,
        resolve: {
            periodoAtividade: PeriodoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-atividade/:id/edit',
        component: PeriodoAtividadeUpdateComponent,
        resolve: {
            periodoAtividade: PeriodoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const periodoAtividadePopupRoute: Routes = [
    {
        path: 'periodo-atividade/:id/delete',
        component: PeriodoAtividadeDeletePopupComponent,
        resolve: {
            periodoAtividade: PeriodoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
