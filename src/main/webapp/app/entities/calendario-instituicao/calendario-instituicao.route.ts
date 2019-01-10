import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';
import { CalendarioInstituicaoService } from './calendario-instituicao.service';
import { CalendarioInstituicaoComponent } from './calendario-instituicao.component';
import { CalendarioInstituicaoDetailComponent } from './calendario-instituicao-detail.component';
import { CalendarioInstituicaoUpdateComponent } from './calendario-instituicao-update.component';
import { CalendarioInstituicaoDeletePopupComponent } from './calendario-instituicao-delete-dialog.component';
import { ICalendarioInstituicao } from 'app/shared/model/calendario-instituicao.model';

@Injectable({ providedIn: 'root' })
export class CalendarioInstituicaoResolve implements Resolve<ICalendarioInstituicao> {
    constructor(private service: CalendarioInstituicaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CalendarioInstituicao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CalendarioInstituicao>) => response.ok),
                map((calendarioInstituicao: HttpResponse<CalendarioInstituicao>) => calendarioInstituicao.body)
            );
        }
        return of(new CalendarioInstituicao());
    }
}

export const calendarioInstituicaoRoute: Routes = [
    {
        path: 'calendario-instituicao',
        component: CalendarioInstituicaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.calendarioInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'calendario-instituicao/:id/view',
        component: CalendarioInstituicaoDetailComponent,
        resolve: {
            calendarioInstituicao: CalendarioInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.calendarioInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'calendario-instituicao/new',
        component: CalendarioInstituicaoUpdateComponent,
        resolve: {
            calendarioInstituicao: CalendarioInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.calendarioInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'calendario-instituicao/:id/edit',
        component: CalendarioInstituicaoUpdateComponent,
        resolve: {
            calendarioInstituicao: CalendarioInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.calendarioInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const calendarioInstituicaoPopupRoute: Routes = [
    {
        path: 'calendario-instituicao/:id/delete',
        component: CalendarioInstituicaoDeletePopupComponent,
        resolve: {
            calendarioInstituicao: CalendarioInstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.calendarioInstituicao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
