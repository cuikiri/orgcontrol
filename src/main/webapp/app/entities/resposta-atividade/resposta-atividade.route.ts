import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespostaAtividade } from 'app/shared/model/resposta-atividade.model';
import { RespostaAtividadeService } from './resposta-atividade.service';
import { RespostaAtividadeComponent } from './resposta-atividade.component';
import { RespostaAtividadeDetailComponent } from './resposta-atividade-detail.component';
import { RespostaAtividadeUpdateComponent } from './resposta-atividade-update.component';
import { RespostaAtividadeDeletePopupComponent } from './resposta-atividade-delete-dialog.component';
import { IRespostaAtividade } from 'app/shared/model/resposta-atividade.model';

@Injectable({ providedIn: 'root' })
export class RespostaAtividadeResolve implements Resolve<IRespostaAtividade> {
    constructor(private service: RespostaAtividadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespostaAtividade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespostaAtividade>) => response.ok),
                map((respostaAtividade: HttpResponse<RespostaAtividade>) => respostaAtividade.body)
            );
        }
        return of(new RespostaAtividade());
    }
}

export const respostaAtividadeRoute: Routes = [
    {
        path: 'resposta-atividade',
        component: RespostaAtividadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respostaAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-atividade/:id/view',
        component: RespostaAtividadeDetailComponent,
        resolve: {
            respostaAtividade: RespostaAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-atividade/new',
        component: RespostaAtividadeUpdateComponent,
        resolve: {
            respostaAtividade: RespostaAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-atividade/:id/edit',
        component: RespostaAtividadeUpdateComponent,
        resolve: {
            respostaAtividade: RespostaAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAtividade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respostaAtividadePopupRoute: Routes = [
    {
        path: 'resposta-atividade/:id/delete',
        component: RespostaAtividadeDeletePopupComponent,
        resolve: {
            respostaAtividade: RespostaAtividadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAtividade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
