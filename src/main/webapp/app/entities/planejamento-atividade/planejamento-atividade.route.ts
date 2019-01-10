import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';
import { PlanejamentoAtividadeService } from './planejamento-atividade.service';
import { PlanejamentoAtividadeComponent } from './planejamento-atividade.component';
import { PlanejamentoAtividadeDetailComponent } from './planejamento-atividade-detail.component';
import { PlanejamentoAtividadeUpdateComponent } from './planejamento-atividade-update.component';
import { PlanejamentoAtividadeDeletePopupComponent } from './planejamento-atividade-delete-dialog.component';
import { IPlanejamentoAtividade } from 'app/shared/model/planejamento-atividade.model';

@Injectable({ providedIn: 'root' })
export class PlanejamentoAtividadeResolve implements Resolve<IPlanejamentoAtividade> {
    constructor(private service: PlanejamentoAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanejamentoAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanejamentoAtividade>) => response.ok),
                map((planejamentoAtividade: HttpResponse<PlanejamentoAtividade>) => planejamentoAtividade.body)
            );
        }
        return of(new PlanejamentoAtividade());
    }
}

export const planejamentoAtividadeRoute: Routes = [
    {
        path: 'planejamento-atividade',
        component: PlanejamentoAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.planejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-atividade/:id/view',
        component: PlanejamentoAtividadeDetailComponent,
        resolve: {
            planejamentoAtividade: PlanejamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-atividade/new',
        component: PlanejamentoAtividadeUpdateComponent,
        resolve: {
            planejamentoAtividade: PlanejamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-atividade/:id/edit',
        component: PlanejamentoAtividadeUpdateComponent,
        resolve: {
            planejamentoAtividade: PlanejamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planejamentoAtividadePopupRoute: Routes = [
    {
        path: 'planejamento-atividade/:id/delete',
        component: PlanejamentoAtividadeDeletePopupComponent,
        resolve: {
            planejamentoAtividade: PlanejamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
