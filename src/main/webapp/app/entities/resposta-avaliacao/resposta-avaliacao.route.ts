import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';
import { RespostaAvaliacaoService } from './resposta-avaliacao.service';
import { RespostaAvaliacaoComponent } from './resposta-avaliacao.component';
import { RespostaAvaliacaoDetailComponent } from './resposta-avaliacao-detail.component';
import { RespostaAvaliacaoUpdateComponent } from './resposta-avaliacao-update.component';
import { RespostaAvaliacaoDeletePopupComponent } from './resposta-avaliacao-delete-dialog.component';
import { IRespostaAvaliacao } from 'app/shared/model/resposta-avaliacao.model';

@Injectable({ providedIn: 'root' })
export class RespostaAvaliacaoResolve implements Resolve<IRespostaAvaliacao> {
    constructor(private service: RespostaAvaliacaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespostaAvaliacao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespostaAvaliacao>) => response.ok),
                map((respostaAvaliacao: HttpResponse<RespostaAvaliacao>) => respostaAvaliacao.body)
            );
        }
        return of(new RespostaAvaliacao());
    }
}

export const respostaAvaliacaoRoute: Routes = [
    {
        path: 'resposta-avaliacao',
        component: RespostaAvaliacaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respostaAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-avaliacao/:id/view',
        component: RespostaAvaliacaoDetailComponent,
        resolve: {
            respostaAvaliacao: RespostaAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-avaliacao/new',
        component: RespostaAvaliacaoUpdateComponent,
        resolve: {
            respostaAvaliacao: RespostaAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-avaliacao/:id/edit',
        component: RespostaAvaliacaoUpdateComponent,
        resolve: {
            respostaAvaliacao: RespostaAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respostaAvaliacaoPopupRoute: Routes = [
    {
        path: 'resposta-avaliacao/:id/delete',
        component: RespostaAvaliacaoDeletePopupComponent,
        resolve: {
            respostaAvaliacao: RespostaAvaliacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAvaliacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
