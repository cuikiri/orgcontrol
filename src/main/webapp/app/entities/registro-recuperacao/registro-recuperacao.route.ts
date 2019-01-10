import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RegistroRecuperacao } from 'app/shared/model/registro-recuperacao.model';
import { RegistroRecuperacaoService } from './registro-recuperacao.service';
import { RegistroRecuperacaoComponent } from './registro-recuperacao.component';
import { RegistroRecuperacaoDetailComponent } from './registro-recuperacao-detail.component';
import { RegistroRecuperacaoUpdateComponent } from './registro-recuperacao-update.component';
import { RegistroRecuperacaoDeletePopupComponent } from './registro-recuperacao-delete-dialog.component';
import { IRegistroRecuperacao } from 'app/shared/model/registro-recuperacao.model';

@Injectable({ providedIn: 'root' })
export class RegistroRecuperacaoResolve implements Resolve<IRegistroRecuperacao> {
    constructor(private service: RegistroRecuperacaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RegistroRecuperacao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RegistroRecuperacao>) => response.ok),
                map((registroRecuperacao: HttpResponse<RegistroRecuperacao>) => registroRecuperacao.body)
            );
        }
        return of(new RegistroRecuperacao());
    }
}

export const registroRecuperacaoRoute: Routes = [
    {
        path: 'registro-recuperacao',
        component: RegistroRecuperacaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.registroRecuperacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'registro-recuperacao/:id/view',
        component: RegistroRecuperacaoDetailComponent,
        resolve: {
            registroRecuperacao: RegistroRecuperacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.registroRecuperacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'registro-recuperacao/new',
        component: RegistroRecuperacaoUpdateComponent,
        resolve: {
            registroRecuperacao: RegistroRecuperacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.registroRecuperacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'registro-recuperacao/:id/edit',
        component: RegistroRecuperacaoUpdateComponent,
        resolve: {
            registroRecuperacao: RegistroRecuperacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.registroRecuperacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const registroRecuperacaoPopupRoute: Routes = [
    {
        path: 'registro-recuperacao/:id/delete',
        component: RegistroRecuperacaoDeletePopupComponent,
        resolve: {
            registroRecuperacao: RegistroRecuperacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.registroRecuperacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
