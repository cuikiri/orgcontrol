import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';
import { ItemAcompanhamentoAtividadeService } from './item-acompanhamento-atividade.service';
import { ItemAcompanhamentoAtividadeComponent } from './item-acompanhamento-atividade.component';
import { ItemAcompanhamentoAtividadeDetailComponent } from './item-acompanhamento-atividade-detail.component';
import { ItemAcompanhamentoAtividadeUpdateComponent } from './item-acompanhamento-atividade-update.component';
import { ItemAcompanhamentoAtividadeDeletePopupComponent } from './item-acompanhamento-atividade-delete-dialog.component';
import { IItemAcompanhamentoAtividade } from 'app/shared/model/item-acompanhamento-atividade.model';

@Injectable({ providedIn: 'root' })
export class ItemAcompanhamentoAtividadeResolve implements Resolve<IItemAcompanhamentoAtividade> {
    constructor(private service: ItemAcompanhamentoAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ItemAcompanhamentoAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ItemAcompanhamentoAtividade>) => response.ok),
                map((itemAcompanhamentoAtividade: HttpResponse<ItemAcompanhamentoAtividade>) => itemAcompanhamentoAtividade.body)
            );
        }
        return of(new ItemAcompanhamentoAtividade());
    }
}

export const itemAcompanhamentoAtividadeRoute: Routes = [
    {
        path: 'item-acompanhamento-atividade',
        component: ItemAcompanhamentoAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.itemAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-acompanhamento-atividade/:id/view',
        component: ItemAcompanhamentoAtividadeDetailComponent,
        resolve: {
            itemAcompanhamentoAtividade: ItemAcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-acompanhamento-atividade/new',
        component: ItemAcompanhamentoAtividadeUpdateComponent,
        resolve: {
            itemAcompanhamentoAtividade: ItemAcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-acompanhamento-atividade/:id/edit',
        component: ItemAcompanhamentoAtividadeUpdateComponent,
        resolve: {
            itemAcompanhamentoAtividade: ItemAcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemAcompanhamentoAtividadePopupRoute: Routes = [
    {
        path: 'item-acompanhamento-atividade/:id/delete',
        component: ItemAcompanhamentoAtividadeDeletePopupComponent,
        resolve: {
            itemAcompanhamentoAtividade: ItemAcompanhamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAcompanhamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
