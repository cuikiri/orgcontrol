import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';
import { AcompanhamentoAtividadeService } from './acompanhamento-atividade.service';
import { AcompanhamentoAtividadeComponent } from './acompanhamento-atividade.component';
import { AcompanhamentoAtividadeDetailComponent } from './acompanhamento-atividade-detail.component';
import { AcompanhamentoAtividadeUpdateComponent } from './acompanhamento-atividade-update.component';
import { AcompanhamentoAtividadeDeletePopupComponent } from './acompanhamento-atividade-delete-dialog.component';
import { IAcompanhamentoAtividade } from 'app/shared/model/acompanhamento-atividade.model';

@Injectable({ providedIn: 'root' })
export class AcompanhamentoAtividadeResolve implements Resolve<IAcompanhamentoAtividade> {
    constructor(private service: AcompanhamentoAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AcompanhamentoAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AcompanhamentoAtividade>) => response.ok),
                map((acompanhamentoAtividade: HttpResponse<AcompanhamentoAtividade>) => acompanhamentoAtividade.body)
            );
        }
        return of(new AcompanhamentoAtividade());
    }
}

export const acompanhamentoAtividadeRoute: Routes = [
    {
        path: 'acompanhamento-atividade',
        component: AcompanhamentoAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.acompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-atividade/:id/view',
        component: AcompanhamentoAtividadeDetailComponent,
        resolve: {
            acompanhamentoAtividade: AcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-atividade/new',
        component: AcompanhamentoAtividadeUpdateComponent,
        resolve: {
            acompanhamentoAtividade: AcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'acompanhamento-atividade/:id/edit',
        component: AcompanhamentoAtividadeUpdateComponent,
        resolve: {
            acompanhamentoAtividade: AcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const acompanhamentoAtividadePopupRoute: Routes = [
    {
        path: 'acompanhamento-atividade/:id/delete',
        component: AcompanhamentoAtividadeDeletePopupComponent,
        resolve: {
            acompanhamentoAtividade: AcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.acompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
