import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';
import { MateriaAcompanhamentoService } from './materia-acompanhamento.service';
import { MateriaAcompanhamentoComponent } from './materia-acompanhamento.component';
import { MateriaAcompanhamentoDetailComponent } from './materia-acompanhamento-detail.component';
import { MateriaAcompanhamentoUpdateComponent } from './materia-acompanhamento-update.component';
import { MateriaAcompanhamentoDeletePopupComponent } from './materia-acompanhamento-delete-dialog.component';
import { IMateriaAcompanhamento } from 'app/shared/model/materia-acompanhamento.model';

@Injectable({ providedIn: 'root' })
export class MateriaAcompanhamentoResolve implements Resolve<IMateriaAcompanhamento> {
    constructor(private service: MateriaAcompanhamentoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MateriaAcompanhamento> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MateriaAcompanhamento>) => response.ok),
                map((materiaAcompanhamento: HttpResponse<MateriaAcompanhamento>) => materiaAcompanhamento.body)
            );
        }
        return of(new MateriaAcompanhamento());
    }
}

export const materiaAcompanhamentoRoute: Routes = [
    {
        path: 'materia-acompanhamento',
        component: MateriaAcompanhamentoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.materiaAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'materia-acompanhamento/:id/view',
        component: MateriaAcompanhamentoDetailComponent,
        resolve: {
            materiaAcompanhamento: MateriaAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.materiaAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'materia-acompanhamento/new',
        component: MateriaAcompanhamentoUpdateComponent,
        resolve: {
            materiaAcompanhamento: MateriaAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.materiaAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'materia-acompanhamento/:id/edit',
        component: MateriaAcompanhamentoUpdateComponent,
        resolve: {
            materiaAcompanhamento: MateriaAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.materiaAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const materiaAcompanhamentoPopupRoute: Routes = [
    {
        path: 'materia-acompanhamento/:id/delete',
        component: MateriaAcompanhamentoDeletePopupComponent,
        resolve: {
            materiaAcompanhamento: MateriaAcompanhamentoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.materiaAcompanhamento.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
