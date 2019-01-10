import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoAtividade } from 'app/shared/model/tipo-atividade.model';
import { TipoAtividadeService } from './tipo-atividade.service';
import { TipoAtividadeComponent } from './tipo-atividade.component';
import { TipoAtividadeDetailComponent } from './tipo-atividade-detail.component';
import { TipoAtividadeUpdateComponent } from './tipo-atividade-update.component';
import { TipoAtividadeDeletePopupComponent } from './tipo-atividade-delete-dialog.component';
import { ITipoAtividade } from 'app/shared/model/tipo-atividade.model';

@Injectable({ providedIn: 'root' })
export class TipoAtividadeResolve implements Resolve<ITipoAtividade> {
    constructor(private service: TipoAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoAtividade>) => response.ok),
                map((tipoAtividade: HttpResponse<TipoAtividade>) => tipoAtividade.body)
            );
        }
        return of(new TipoAtividade());
    }
}

export const tipoAtividadeRoute: Routes = [
    {
        path: 'tipo-atividade',
        component: TipoAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-atividade/:id/view',
        component: TipoAtividadeDetailComponent,
        resolve: {
            tipoAtividade: TipoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-atividade/new',
        component: TipoAtividadeUpdateComponent,
        resolve: {
            tipoAtividade: TipoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-atividade/:id/edit',
        component: TipoAtividadeUpdateComponent,
        resolve: {
            tipoAtividade: TipoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoAtividadePopupRoute: Routes = [
    {
        path: 'tipo-atividade/:id/delete',
        component: TipoAtividadeDeletePopupComponent,
        resolve: {
            tipoAtividade: TipoAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
