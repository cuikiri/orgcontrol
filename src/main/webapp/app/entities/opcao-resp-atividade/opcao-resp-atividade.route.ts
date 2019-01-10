import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { OpcaoRespAtividade } from 'app/shared/model/opcao-resp-atividade.model';
import { OpcaoRespAtividadeService } from './opcao-resp-atividade.service';
import { OpcaoRespAtividadeComponent } from './opcao-resp-atividade.component';
import { OpcaoRespAtividadeDetailComponent } from './opcao-resp-atividade-detail.component';
import { OpcaoRespAtividadeUpdateComponent } from './opcao-resp-atividade-update.component';
import { OpcaoRespAtividadeDeletePopupComponent } from './opcao-resp-atividade-delete-dialog.component';
import { IOpcaoRespAtividade } from 'app/shared/model/opcao-resp-atividade.model';

@Injectable({ providedIn: 'root' })
export class OpcaoRespAtividadeResolve implements Resolve<IOpcaoRespAtividade> {
    constructor(private service: OpcaoRespAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<OpcaoRespAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<OpcaoRespAtividade>) => response.ok),
                map((opcaoRespAtividade: HttpResponse<OpcaoRespAtividade>) => opcaoRespAtividade.body)
            );
        }
        return of(new OpcaoRespAtividade());
    }
}

export const opcaoRespAtividadeRoute: Routes = [
    {
        path: 'opcao-resp-atividade',
        component: OpcaoRespAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.opcaoRespAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-atividade/:id/view',
        component: OpcaoRespAtividadeDetailComponent,
        resolve: {
            opcaoRespAtividade: OpcaoRespAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-atividade/new',
        component: OpcaoRespAtividadeUpdateComponent,
        resolve: {
            opcaoRespAtividade: OpcaoRespAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'opcao-resp-atividade/:id/edit',
        component: OpcaoRespAtividadeUpdateComponent,
        resolve: {
            opcaoRespAtividade: OpcaoRespAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const opcaoRespAtividadePopupRoute: Routes = [
    {
        path: 'opcao-resp-atividade/:id/delete',
        component: OpcaoRespAtividadeDeletePopupComponent,
        resolve: {
            opcaoRespAtividade: OpcaoRespAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.opcaoRespAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
