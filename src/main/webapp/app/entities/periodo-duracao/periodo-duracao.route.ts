import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PeriodoDuracao } from 'app/shared/model/periodo-duracao.model';
import { PeriodoDuracaoService } from './periodo-duracao.service';
import { PeriodoDuracaoComponent } from './periodo-duracao.component';
import { PeriodoDuracaoDetailComponent } from './periodo-duracao-detail.component';
import { PeriodoDuracaoUpdateComponent } from './periodo-duracao-update.component';
import { PeriodoDuracaoDeletePopupComponent } from './periodo-duracao-delete-dialog.component';
import { IPeriodoDuracao } from 'app/shared/model/periodo-duracao.model';

@Injectable({ providedIn: 'root' })
export class PeriodoDuracaoResolve implements Resolve<IPeriodoDuracao> {
    constructor(private service: PeriodoDuracaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PeriodoDuracao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PeriodoDuracao>) => response.ok),
                map((periodoDuracao: HttpResponse<PeriodoDuracao>) => periodoDuracao.body)
            );
        }
        return of(new PeriodoDuracao());
    }
}

export const periodoDuracaoRoute: Routes = [
    {
        path: 'periodo-duracao',
        component: PeriodoDuracaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.periodoDuracao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-duracao/:id/view',
        component: PeriodoDuracaoDetailComponent,
        resolve: {
            periodoDuracao: PeriodoDuracaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoDuracao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-duracao/new',
        component: PeriodoDuracaoUpdateComponent,
        resolve: {
            periodoDuracao: PeriodoDuracaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoDuracao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'periodo-duracao/:id/edit',
        component: PeriodoDuracaoUpdateComponent,
        resolve: {
            periodoDuracao: PeriodoDuracaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoDuracao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const periodoDuracaoPopupRoute: Routes = [
    {
        path: 'periodo-duracao/:id/delete',
        component: PeriodoDuracaoDeletePopupComponent,
        resolve: {
            periodoDuracao: PeriodoDuracaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.periodoDuracao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
