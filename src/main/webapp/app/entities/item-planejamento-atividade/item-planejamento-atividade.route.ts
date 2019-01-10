import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ItemPlanejamentoAtividade } from 'app/shared/model/item-planejamento-atividade.model';
import { ItemPlanejamentoAtividadeService } from './item-planejamento-atividade.service';
import { ItemPlanejamentoAtividadeComponent } from './item-planejamento-atividade.component';
import { ItemPlanejamentoAtividadeDetailComponent } from './item-planejamento-atividade-detail.component';
import { ItemPlanejamentoAtividadeUpdateComponent } from './item-planejamento-atividade-update.component';
import { ItemPlanejamentoAtividadeDeletePopupComponent } from './item-planejamento-atividade-delete-dialog.component';
import { IItemPlanejamentoAtividade } from 'app/shared/model/item-planejamento-atividade.model';

@Injectable({ providedIn: 'root' })
export class ItemPlanejamentoAtividadeResolve implements Resolve<IItemPlanejamentoAtividade> {
    constructor(private service: ItemPlanejamentoAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ItemPlanejamentoAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ItemPlanejamentoAtividade>) => response.ok),
                map((itemPlanejamentoAtividade: HttpResponse<ItemPlanejamentoAtividade>) => itemPlanejamentoAtividade.body)
            );
        }
        return of(new ItemPlanejamentoAtividade());
    }
}

export const itemPlanejamentoAtividadeRoute: Routes = [
    {
        path: 'item-planejamento-atividade',
        component: ItemPlanejamentoAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.itemPlanejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-atividade/:id/view',
        component: ItemPlanejamentoAtividadeDetailComponent,
        resolve: {
            itemPlanejamentoAtividade: ItemPlanejamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-atividade/new',
        component: ItemPlanejamentoAtividadeUpdateComponent,
        resolve: {
            itemPlanejamentoAtividade: ItemPlanejamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-atividade/:id/edit',
        component: ItemPlanejamentoAtividadeUpdateComponent,
        resolve: {
            itemPlanejamentoAtividade: ItemPlanejamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemPlanejamentoAtividadePopupRoute: Routes = [
    {
        path: 'item-planejamento-atividade/:id/delete',
        component: ItemPlanejamentoAtividadeDeletePopupComponent,
        resolve: {
            itemPlanejamentoAtividade: ItemPlanejamentoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
