import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ExameMedico } from 'app/shared/model/exame-medico.model';
import { ExameMedicoService } from './exame-medico.service';
import { ExameMedicoComponent } from './exame-medico.component';
import { ExameMedicoDetailComponent } from './exame-medico-detail.component';
import { ExameMedicoUpdateComponent } from './exame-medico-update.component';
import { ExameMedicoDeletePopupComponent } from './exame-medico-delete-dialog.component';
import { IExameMedico } from 'app/shared/model/exame-medico.model';

@Injectable({ providedIn: 'root' })
export class ExameMedicoResolve implements Resolve<IExameMedico> {
    constructor(private service: ExameMedicoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ExameMedico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ExameMedico>) => response.ok),
                map((exameMedico: HttpResponse<ExameMedico>) => exameMedico.body)
            );
        }
        return of(new ExameMedico());
    }
}

export const exameMedicoRoute: Routes = [
    {
        path: 'exame-medico',
        component: ExameMedicoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.exameMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'exame-medico/:id/view',
        component: ExameMedicoDetailComponent,
        resolve: {
            exameMedico: ExameMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.exameMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'exame-medico/new',
        component: ExameMedicoUpdateComponent,
        resolve: {
            exameMedico: ExameMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.exameMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'exame-medico/:id/edit',
        component: ExameMedicoUpdateComponent,
        resolve: {
            exameMedico: ExameMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.exameMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const exameMedicoPopupRoute: Routes = [
    {
        path: 'exame-medico/:id/delete',
        component: ExameMedicoDeletePopupComponent,
        resolve: {
            exameMedico: ExameMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.exameMedico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
