import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ItemPlanejamentoEnsino } from 'app/shared/model/item-planejamento-ensino.model';
import { ItemPlanejamentoEnsinoService } from './item-planejamento-ensino.service';
import { ItemPlanejamentoEnsinoComponent } from './item-planejamento-ensino.component';
import { ItemPlanejamentoEnsinoDetailComponent } from './item-planejamento-ensino-detail.component';
import { ItemPlanejamentoEnsinoUpdateComponent } from './item-planejamento-ensino-update.component';
import { ItemPlanejamentoEnsinoDeletePopupComponent } from './item-planejamento-ensino-delete-dialog.component';
import { IItemPlanejamentoEnsino } from 'app/shared/model/item-planejamento-ensino.model';

@Injectable({ providedIn: 'root' })
export class ItemPlanejamentoEnsinoResolve implements Resolve<IItemPlanejamentoEnsino> {
    constructor(private service: ItemPlanejamentoEnsinoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ItemPlanejamentoEnsino> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ItemPlanejamentoEnsino>) => response.ok),
                map((itemPlanejamentoEnsino: HttpResponse<ItemPlanejamentoEnsino>) => itemPlanejamentoEnsino.body)
            );
        }
        return of(new ItemPlanejamentoEnsino());
    }
}

export const itemPlanejamentoEnsinoRoute: Routes = [
    {
        path: 'item-planejamento-ensino',
        component: ItemPlanejamentoEnsinoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.itemPlanejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-ensino/:id/view',
        component: ItemPlanejamentoEnsinoDetailComponent,
        resolve: {
            itemPlanejamentoEnsino: ItemPlanejamentoEnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-ensino/new',
        component: ItemPlanejamentoEnsinoUpdateComponent,
        resolve: {
            itemPlanejamentoEnsino: ItemPlanejamentoEnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-ensino/:id/edit',
        component: ItemPlanejamentoEnsinoUpdateComponent,
        resolve: {
            itemPlanejamentoEnsino: ItemPlanejamentoEnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemPlanejamentoEnsinoPopupRoute: Routes = [
    {
        path: 'item-planejamento-ensino/:id/delete',
        component: ItemPlanejamentoEnsinoDeletePopupComponent,
        resolve: {
            itemPlanejamentoEnsino: ItemPlanejamentoEnsinoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoEnsino.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
