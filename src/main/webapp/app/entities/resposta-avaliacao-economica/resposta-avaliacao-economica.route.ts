import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';
import { RespostaAvaliacaoEconomicaService } from './resposta-avaliacao-economica.service';
import { RespostaAvaliacaoEconomicaComponent } from './resposta-avaliacao-economica.component';
import { RespostaAvaliacaoEconomicaDetailComponent } from './resposta-avaliacao-economica-detail.component';
import { RespostaAvaliacaoEconomicaUpdateComponent } from './resposta-avaliacao-economica-update.component';
import { RespostaAvaliacaoEconomicaDeletePopupComponent } from './resposta-avaliacao-economica-delete-dialog.component';
import { IRespostaAvaliacaoEconomica } from 'app/shared/model/resposta-avaliacao-economica.model';

@Injectable({ providedIn: 'root' })
export class RespostaAvaliacaoEconomicaResolve implements Resolve<IRespostaAvaliacaoEconomica> {
    constructor(private service: RespostaAvaliacaoEconomicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RespostaAvaliacaoEconomica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RespostaAvaliacaoEconomica>) => response.ok),
                map((respostaAvaliacaoEconomica: HttpResponse<RespostaAvaliacaoEconomica>) => respostaAvaliacaoEconomica.body)
            );
        }
        return of(new RespostaAvaliacaoEconomica());
    }
}

export const respostaAvaliacaoEconomicaRoute: Routes = [
    {
        path: 'resposta-avaliacao-economica',
        component: RespostaAvaliacaoEconomicaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.respostaAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-avaliacao-economica/:id/view',
        component: RespostaAvaliacaoEconomicaDetailComponent,
        resolve: {
            respostaAvaliacaoEconomica: RespostaAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-avaliacao-economica/new',
        component: RespostaAvaliacaoEconomicaUpdateComponent,
        resolve: {
            respostaAvaliacaoEconomica: RespostaAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'resposta-avaliacao-economica/:id/edit',
        component: RespostaAvaliacaoEconomicaUpdateComponent,
        resolve: {
            respostaAvaliacaoEconomica: RespostaAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const respostaAvaliacaoEconomicaPopupRoute: Routes = [
    {
        path: 'resposta-avaliacao-economica/:id/delete',
        component: RespostaAvaliacaoEconomicaDeletePopupComponent,
        resolve: {
            respostaAvaliacaoEconomica: RespostaAvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.respostaAvaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
