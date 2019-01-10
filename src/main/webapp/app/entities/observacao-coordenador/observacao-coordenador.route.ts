import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';
import { ObservacaoCoordenadorService } from './observacao-coordenador.service';
import { ObservacaoCoordenadorComponent } from './observacao-coordenador.component';
import { ObservacaoCoordenadorDetailComponent } from './observacao-coordenador-detail.component';
import { ObservacaoCoordenadorUpdateComponent } from './observacao-coordenador-update.component';
import { ObservacaoCoordenadorDeletePopupComponent } from './observacao-coordenador-delete-dialog.component';
import { IObservacaoCoordenador } from 'app/shared/model/observacao-coordenador.model';

@Injectable({ providedIn: 'root' })
export class ObservacaoCoordenadorResolve implements Resolve<IObservacaoCoordenador> {
    constructor(private service: ObservacaoCoordenadorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObservacaoCoordenador> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ObservacaoCoordenador>) => response.ok),
                map((observacaoCoordenador: HttpResponse<ObservacaoCoordenador>) => observacaoCoordenador.body)
            );
        }
        return of(new ObservacaoCoordenador());
    }
}

export const observacaoCoordenadorRoute: Routes = [
    {
        path: 'observacao-coordenador',
        component: ObservacaoCoordenadorComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.observacaoCoordenador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacao-coordenador/:id/view',
        component: ObservacaoCoordenadorDetailComponent,
        resolve: {
            observacaoCoordenador: ObservacaoCoordenadorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.observacaoCoordenador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacao-coordenador/new',
        component: ObservacaoCoordenadorUpdateComponent,
        resolve: {
            observacaoCoordenador: ObservacaoCoordenadorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.observacaoCoordenador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacao-coordenador/:id/edit',
        component: ObservacaoCoordenadorUpdateComponent,
        resolve: {
            observacaoCoordenador: ObservacaoCoordenadorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.observacaoCoordenador.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const observacaoCoordenadorPopupRoute: Routes = [
    {
        path: 'observacao-coordenador/:id/delete',
        component: ObservacaoCoordenadorDeletePopupComponent,
        resolve: {
            observacaoCoordenador: ObservacaoCoordenadorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.observacaoCoordenador.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
