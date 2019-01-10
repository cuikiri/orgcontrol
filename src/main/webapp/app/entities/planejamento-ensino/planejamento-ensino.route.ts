import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';
import { PlanejamentoEnsinoService } from './planejamento-ensino.service';
import { PlanejamentoEnsinoComponent } from './planejamento-ensino.component';
import { PlanejamentoEnsinoDetailComponent } from './planejamento-ensino-detail.component';
import { PlanejamentoEnsinoUpdateComponent } from './planejamento-ensino-update.component';
import { PlanejamentoEnsinoDeletePopupComponent } from './planejamento-ensino-delete-dialog.component';
import { IPlanejamentoEnsino } from 'app/shared/model/planejamento-ensino.model';

@Injectable({ providedIn: 'root' })
export class PlanejamentoEnsinoResolve implements Resolve<IPlanejamentoEnsino> {
    constructor(private service: PlanejamentoEnsinoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanejamentoEnsino> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanejamentoEnsino>) => response.ok),
                map((planejamentoEnsino: HttpResponse<PlanejamentoEnsino>) => planejamentoEnsino.body)
            );
        }
        return of(new PlanejamentoEnsino());
    }
}

export const planejamentoEnsinoRoute: Routes = [
    {
        path: 'planejamento-ensino',
        component: PlanejamentoEnsinoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.planejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-ensino/:id/view',
        component: PlanejamentoEnsinoDetailComponent,
        resolve: {
            planejamentoEnsino: PlanejamentoEnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-ensino/new',
        component: PlanejamentoEnsinoUpdateComponent,
        resolve: {
            planejamentoEnsino: PlanejamentoEnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-ensino/:id/edit',
        component: PlanejamentoEnsinoUpdateComponent,
        resolve: {
            planejamentoEnsino: PlanejamentoEnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planejamentoEnsinoPopupRoute: Routes = [
    {
        path: 'planejamento-ensino/:id/delete',
        component: PlanejamentoEnsinoDeletePopupComponent,
        resolve: {
            planejamentoEnsino: PlanejamentoEnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
