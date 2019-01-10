import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';
import { AvaliacaoEconomicaService } from './avaliacao-economica.service';
import { AvaliacaoEconomicaComponent } from './avaliacao-economica.component';
import { AvaliacaoEconomicaDetailComponent } from './avaliacao-economica-detail.component';
import { AvaliacaoEconomicaUpdateComponent } from './avaliacao-economica-update.component';
import { AvaliacaoEconomicaDeletePopupComponent } from './avaliacao-economica-delete-dialog.component';
import { IAvaliacaoEconomica } from 'app/shared/model/avaliacao-economica.model';

@Injectable({ providedIn: 'root' })
export class AvaliacaoEconomicaResolve implements Resolve<IAvaliacaoEconomica> {
    constructor(private service: AvaliacaoEconomicaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AvaliacaoEconomica> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AvaliacaoEconomica>) => response.ok),
                map((avaliacaoEconomica: HttpResponse<AvaliacaoEconomica>) => avaliacaoEconomica.body)
            );
        }
        return of(new AvaliacaoEconomica());
    }
}

export const avaliacaoEconomicaRoute: Routes = [
    {
        path: 'avaliacao-economica',
        component: AvaliacaoEconomicaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.avaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'avaliacao-economica/:id/view',
        component: AvaliacaoEconomicaDetailComponent,
        resolve: {
            avaliacaoEconomica: AvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.avaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'avaliacao-economica/new',
        component: AvaliacaoEconomicaUpdateComponent,
        resolve: {
            avaliacaoEconomica: AvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.avaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'avaliacao-economica/:id/edit',
        component: AvaliacaoEconomicaUpdateComponent,
        resolve: {
            avaliacaoEconomica: AvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.avaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const avaliacaoEconomicaPopupRoute: Routes = [
    {
        path: 'avaliacao-economica/:id/delete',
        component: AvaliacaoEconomicaDeletePopupComponent,
        resolve: {
            avaliacaoEconomica: AvaliacaoEconomicaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.avaliacaoEconomica.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
