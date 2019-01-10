import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Colaborador } from 'app/shared/model/colaborador.model';
import { ColaboradorService } from './colaborador.service';
import { ColaboradorComponent } from './colaborador.component';
import { ColaboradorDetailComponent } from './colaborador-detail.component';
import { ColaboradorUpdateComponent } from './colaborador-update.component';
import { ColaboradorDeletePopupComponent } from './colaborador-delete-dialog.component';
import { IColaborador } from 'app/shared/model/colaborador.model';

@Injectable({ providedIn: 'root' })
export class ColaboradorResolve implements Resolve<IColaborador> {
    constructor(private service: ColaboradorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Colaborador> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Colaborador>) => response.ok),
                map((colaborador: HttpResponse<Colaborador>) => colaborador.body)
            );
        }
        return of(new Colaborador());
    }
}

export const colaboradorRoute: Routes = [
    {
        path: 'colaborador',
        component: ColaboradorComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.colaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'colaborador/:id/view',
        component: ColaboradorDetailComponent,
        resolve: {
            colaborador: ColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.colaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'colaborador/new',
        component: ColaboradorUpdateComponent,
        resolve: {
            colaborador: ColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.colaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'colaborador/:id/edit',
        component: ColaboradorUpdateComponent,
        resolve: {
            colaborador: ColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.colaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const colaboradorPopupRoute: Routes = [
    {
        path: 'colaborador/:id/delete',
        component: ColaboradorDeletePopupComponent,
        resolve: {
            colaborador: ColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.colaborador.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
