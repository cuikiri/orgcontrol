import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ObservacaoProfessor } from 'app/shared/model/observacao-professor.model';
import { ObservacaoProfessorService } from './observacao-professor.service';
import { ObservacaoProfessorComponent } from './observacao-professor.component';
import { ObservacaoProfessorDetailComponent } from './observacao-professor-detail.component';
import { ObservacaoProfessorUpdateComponent } from './observacao-professor-update.component';
import { ObservacaoProfessorDeletePopupComponent } from './observacao-professor-delete-dialog.component';
import { IObservacaoProfessor } from 'app/shared/model/observacao-professor.model';

@Injectable({ providedIn: 'root' })
export class ObservacaoProfessorResolve implements Resolve<IObservacaoProfessor> {
    constructor(private service: ObservacaoProfessorService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ObservacaoProfessor> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ObservacaoProfessor>) => response.ok),
                map((observacaoProfessor: HttpResponse<ObservacaoProfessor>) => observacaoProfessor.body)
            );
        }
        return of(new ObservacaoProfessor());
    }
}

export const observacaoProfessorRoute: Routes = [
    {
        path: 'observacao-professor',
        component: ObservacaoProfessorComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.observacaoProfessor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacao-professor/:id/view',
        component: ObservacaoProfessorDetailComponent,
        resolve: {
            observacaoProfessor: ObservacaoProfessorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.observacaoProfessor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacao-professor/new',
        component: ObservacaoProfessorUpdateComponent,
        resolve: {
            observacaoProfessor: ObservacaoProfessorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.observacaoProfessor.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'observacao-professor/:id/edit',
        component: ObservacaoProfessorUpdateComponent,
        resolve: {
            observacaoProfessor: ObservacaoProfessorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.observacaoProfessor.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const observacaoProfessorPopupRoute: Routes = [
    {
        path: 'observacao-professor/:id/delete',
        component: ObservacaoProfessorDeletePopupComponent,
        resolve: {
            observacaoProfessor: ObservacaoProfessorResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.observacaoProfessor.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
