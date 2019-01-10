import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';
import { TipoAcompanhamentoAtividadeService } from './tipo-acompanhamento-atividade.service';
import { TipoAcompanhamentoAtividadeComponent } from './tipo-acompanhamento-atividade.component';
import { TipoAcompanhamentoAtividadeDetailComponent } from './tipo-acompanhamento-atividade-detail.component';
import { TipoAcompanhamentoAtividadeUpdateComponent } from './tipo-acompanhamento-atividade-update.component';
import { TipoAcompanhamentoAtividadeDeletePopupComponent } from './tipo-acompanhamento-atividade-delete-dialog.component';
import { ITipoAcompanhamentoAtividade } from 'app/shared/model/tipo-acompanhamento-atividade.model';

@Injectable({ providedIn: 'root' })
export class TipoAcompanhamentoAtividadeResolve implements Resolve<ITipoAcompanhamentoAtividade> {
    constructor(private service: TipoAcompanhamentoAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoAcompanhamentoAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoAcompanhamentoAtividade>) => response.ok),
                map((tipoAcompanhamentoAtividade: HttpResponse<TipoAcompanhamentoAtividade>) => tipoAcompanhamentoAtividade.body)
            );
        }
        return of(new TipoAcompanhamentoAtividade());
    }
}

export const tipoAcompanhamentoAtividadeRoute: Routes = [
    {
        path: 'tipo-acompanhamento-atividade',
        component: TipoAcompanhamentoAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-acompanhamento-atividade/:id/view',
        component: TipoAcompanhamentoAtividadeDetailComponent,
        resolve: {
            tipoAcompanhamentoAtividade: TipoAcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-acompanhamento-atividade/new',
        component: TipoAcompanhamentoAtividadeUpdateComponent,
        resolve: {
            tipoAcompanhamentoAtividade: TipoAcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-acompanhamento-atividade/:id/edit',
        component: TipoAcompanhamentoAtividadeUpdateComponent,
        resolve: {
            tipoAcompanhamentoAtividade: TipoAcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoAcompanhamentoAtividadePopupRoute: Routes = [
    {
        path: 'tipo-acompanhamento-atividade/:id/delete',
        component: TipoAcompanhamentoAtividadeDeletePopupComponent,
        resolve: {
            tipoAcompanhamentoAtividade: TipoAcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
