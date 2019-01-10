import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ItemAvaliacao } from 'app/shared/model/item-avaliacao.model';
import { ItemAvaliacaoService } from './item-avaliacao.service';
import { ItemAvaliacaoComponent } from './item-avaliacao.component';
import { ItemAvaliacaoDetailComponent } from './item-avaliacao-detail.component';
import { ItemAvaliacaoUpdateComponent } from './item-avaliacao-update.component';
import { ItemAvaliacaoDeletePopupComponent } from './item-avaliacao-delete-dialog.component';
import { IItemAvaliacao } from 'app/shared/model/item-avaliacao.model';

@Injectable({ providedIn: 'root' })
export class ItemAvaliacaoResolve implements Resolve<IItemAvaliacao> {
    constructor(private service: ItemAvaliacaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ItemAvaliacao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ItemAvaliacao>) => response.ok),
                map((itemAvaliacao: HttpResponse<ItemAvaliacao>) => itemAvaliacao.body)
            );
        }
        return of(new ItemAvaliacao());
    }
}

export const itemAvaliacaoRoute: Routes = [
    {
        path: 'item-avaliacao',
        component: ItemAvaliacaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.itemAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-avaliacao/:id/view',
        component: ItemAvaliacaoDetailComponent,
        resolve: {
            itemAvaliacao: ItemAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-avaliacao/new',
        component: ItemAvaliacaoUpdateComponent,
        resolve: {
            itemAvaliacao: ItemAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-avaliacao/:id/edit',
        component: ItemAvaliacaoUpdateComponent,
        resolve: {
            itemAvaliacao: ItemAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemAvaliacaoPopupRoute: Routes = [
    {
        path: 'item-avaliacao/:id/delete',
        component: ItemAvaliacaoDeletePopupComponent,
        resolve: {
            itemAvaliacao: ItemAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
