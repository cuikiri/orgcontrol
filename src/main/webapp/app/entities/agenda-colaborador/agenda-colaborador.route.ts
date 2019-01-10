import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AgendaColaborador } from 'app/shared/model/agenda-colaborador.model';
import { AgendaColaboradorService } from './agenda-colaborador.service';
import { AgendaColaboradorComponent } from './agenda-colaborador.component';
import { AgendaColaboradorDetailComponent } from './agenda-colaborador-detail.component';
import { AgendaColaboradorUpdateComponent } from './agenda-colaborador-update.component';
import { AgendaColaboradorDeletePopupComponent } from './agenda-colaborador-delete-dialog.component';
import { IAgendaColaborador } from 'app/shared/model/agenda-colaborador.model';

@Injectable({ providedIn: 'root' })
export class AgendaColaboradorResolve implements Resolve<IAgendaColaborador> {
    constructor(private service: AgendaColaboradorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AgendaColaborador> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AgendaColaborador>) => response.ok),
                map((agendaColaborador: HttpResponse<AgendaColaborador>) => agendaColaborador.body)
            );
        }
        return of(new AgendaColaborador());
    }
}

export const agendaColaboradorRoute: Routes = [
    {
        path: 'agenda-colaborador',
        component: AgendaColaboradorComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.agendaColaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'agenda-colaborador/:id/view',
        component: AgendaColaboradorDetailComponent,
        resolve: {
            agendaColaborador: AgendaColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.agendaColaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'agenda-colaborador/new',
        component: AgendaColaboradorUpdateComponent,
        resolve: {
            agendaColaborador: AgendaColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.agendaColaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'agenda-colaborador/:id/edit',
        component: AgendaColaboradorUpdateComponent,
        resolve: {
            agendaColaborador: AgendaColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.agendaColaborador.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const agendaColaboradorPopupRoute: Routes = [
    {
        path: 'agenda-colaborador/:id/delete',
        component: AgendaColaboradorDeletePopupComponent,
        resolve: {
            agendaColaborador: AgendaColaboradorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.agendaColaborador.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
