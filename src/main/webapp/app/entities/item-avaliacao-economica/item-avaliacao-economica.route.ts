import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';
import { ItemAvaliacaoEconomicaService } from './item-avaliacao-economica.service';
import { ItemAvaliacaoEconomicaComponent } from './item-avaliacao-economica.component';
import { ItemAvaliacaoEconomicaDetailComponent } from './item-avaliacao-economica-detail.component';
import { ItemAvaliacaoEconomicaUpdateComponent } from './item-avaliacao-economica-update.component';
import { ItemAvaliacaoEconomicaDeletePopupComponent } from './item-avaliacao-economica-delete-dialog.component';
import { IItemAvaliacaoEconomica } from 'app/shared/model/item-avaliacao-economica.model';

@Injectable({ providedIn: 'root' })
export class ItemAvaliacaoEconomicaResolve implements Resolve<IItemAvaliacaoEconomica> {
    constructor(private service: ItemAvaliacaoEconomicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ItemAvaliacaoEconomica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ItemAvaliacaoEconomica>) => response.ok),
                map((itemAvaliacaoEconomica: HttpResponse<ItemAvaliacaoEconomica>) => itemAvaliacaoEconomica.body)
            );
        }
        return of(new ItemAvaliacaoEconomica());
    }
}

export const itemAvaliacaoEconomicaRoute: Routes = [
    {
        path: 'item-avaliacao-economica',
        component: ItemAvaliacaoEconomicaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.itemAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-avaliacao-economica/:id/view',
        component: ItemAvaliacaoEconomicaDetailComponent,
        resolve: {
            itemAvaliacaoEconomica: ItemAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-avaliacao-economica/new',
        component: ItemAvaliacaoEconomicaUpdateComponent,
        resolve: {
            itemAvaliacaoEconomica: ItemAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-avaliacao-economica/:id/edit',
        component: ItemAvaliacaoEconomicaUpdateComponent,
        resolve: {
            itemAvaliacaoEconomica: ItemAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemAvaliacaoEconomicaPopupRoute: Routes = [
    {
        path: 'item-avaliacao-economica/:id/delete',
        component: ItemAvaliacaoEconomicaDeletePopupComponent,
        resolve: {
            itemAvaliacaoEconomica: ItemAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
