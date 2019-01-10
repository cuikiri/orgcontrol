import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ItemPlanejamentoInstituicao } from 'app/shared/model/item-planejamento-instituicao.model';
import { ItemPlanejamentoInstituicaoService } from './item-planejamento-instituicao.service';
import { ItemPlanejamentoInstituicaoComponent } from './item-planejamento-instituicao.component';
import { ItemPlanejamentoInstituicaoDetailComponent } from './item-planejamento-instituicao-detail.component';
import { ItemPlanejamentoInstituicaoUpdateComponent } from './item-planejamento-instituicao-update.component';
import { ItemPlanejamentoInstituicaoDeletePopupComponent } from './item-planejamento-instituicao-delete-dialog.component';
import { IItemPlanejamentoInstituicao } from 'app/shared/model/item-planejamento-instituicao.model';

@Injectable({ providedIn: 'root' })
export class ItemPlanejamentoInstituicaoResolve implements Resolve<IItemPlanejamentoInstituicao> {
    constructor(private service: ItemPlanejamentoInstituicaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ItemPlanejamentoInstituicao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ItemPlanejamentoInstituicao>) => response.ok),
                map((itemPlanejamentoInstituicao: HttpResponse<ItemPlanejamentoInstituicao>) => itemPlanejamentoInstituicao.body)
            );
        }
        return of(new ItemPlanejamentoInstituicao());
    }
}

export const itemPlanejamentoInstituicaoRoute: Routes = [
    {
        path: 'item-planejamento-instituicao',
        component: ItemPlanejamentoInstituicaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.itemPlanejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-instituicao/:id/view',
        component: ItemPlanejamentoInstituicaoDetailComponent,
        resolve: {
            itemPlanejamentoInstituicao: ItemPlanejamentoInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-instituicao/new',
        component: ItemPlanejamentoInstituicaoUpdateComponent,
        resolve: {
            itemPlanejamentoInstituicao: ItemPlanejamentoInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'item-planejamento-instituicao/:id/edit',
        component: ItemPlanejamentoInstituicaoUpdateComponent,
        resolve: {
            itemPlanejamentoInstituicao: ItemPlanejamentoInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const itemPlanejamentoInstituicaoPopupRoute: Routes = [
    {
        path: 'item-planejamento-instituicao/:id/delete',
        component: ItemPlanejamentoInstituicaoDeletePopupComponent,
        resolve: {
            itemPlanejamentoInstituicao: ItemPlanejamentoInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.itemPlanejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
