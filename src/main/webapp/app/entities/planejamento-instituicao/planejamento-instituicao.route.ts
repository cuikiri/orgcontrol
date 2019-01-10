import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';
import { PlanejamentoInstituicaoService } from './planejamento-instituicao.service';
import { PlanejamentoInstituicaoComponent } from './planejamento-instituicao.component';
import { PlanejamentoInstituicaoDetailComponent } from './planejamento-instituicao-detail.component';
import { PlanejamentoInstituicaoUpdateComponent } from './planejamento-instituicao-update.component';
import { PlanejamentoInstituicaoDeletePopupComponent } from './planejamento-instituicao-delete-dialog.component';
import { IPlanejamentoInstituicao } from 'app/shared/model/planejamento-instituicao.model';

@Injectable({ providedIn: 'root' })
export class PlanejamentoInstituicaoResolve implements Resolve<IPlanejamentoInstituicao> {
    constructor(private service: PlanejamentoInstituicaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanejamentoInstituicao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanejamentoInstituicao>) => response.ok),
                map((planejamentoInstituicao: HttpResponse<PlanejamentoInstituicao>) => planejamentoInstituicao.body)
            );
        }
        return of(new PlanejamentoInstituicao());
    }
}

export const planejamentoInstituicaoRoute: Routes = [
    {
        path: 'planejamento-instituicao',
        component: PlanejamentoInstituicaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.planejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-instituicao/:id/view',
        component: PlanejamentoInstituicaoDetailComponent,
        resolve: {
            planejamentoInstituicao: PlanejamentoInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-instituicao/new',
        component: PlanejamentoInstituicaoUpdateComponent,
        resolve: {
            planejamentoInstituicao: PlanejamentoInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'planejamento-instituicao/:id/edit',
        component: PlanejamentoInstituicaoUpdateComponent,
        resolve: {
            planejamentoInstituicao: PlanejamentoInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planejamentoInstituicaoPopupRoute: Routes = [
    {
        path: 'planejamento-instituicao/:id/delete',
        component: PlanejamentoInstituicaoDeletePopupComponent,
        resolve: {
            planejamentoInstituicao: PlanejamentoInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.planejamentoInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
