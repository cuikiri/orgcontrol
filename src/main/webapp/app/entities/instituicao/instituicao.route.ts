import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Instituicao } from 'app/shared/model/instituicao.model';
import { InstituicaoService } from './instituicao.service';
import { InstituicaoComponent } from './instituicao.component';
import { InstituicaoDetailComponent } from './instituicao-detail.component';
import { InstituicaoUpdateComponent } from './instituicao-update.component';
import { InstituicaoDeletePopupComponent } from './instituicao-delete-dialog.component';
import { IInstituicao } from 'app/shared/model/instituicao.model';

@Injectable({ providedIn: 'root' })
export class InstituicaoResolve implements Resolve<IInstituicao> {
    constructor(private service: InstituicaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Instituicao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Instituicao>) => response.ok),
                map((instituicao: HttpResponse<Instituicao>) => instituicao.body)
            );
        }
        return of(new Instituicao());
    }
}

export const instituicaoRoute: Routes = [
    {
        path: 'instituicao',
        component: InstituicaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.instituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instituicao/:id/view',
        component: InstituicaoDetailComponent,
        resolve: {
            instituicao: InstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.instituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instituicao/new',
        component: InstituicaoUpdateComponent,
        resolve: {
            instituicao: InstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.instituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'instituicao/:id/edit',
        component: InstituicaoUpdateComponent,
        resolve: {
            instituicao: InstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.instituicao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const instituicaoPopupRoute: Routes = [
    {
        path: 'instituicao/:id/delete',
        component: InstituicaoDeletePopupComponent,
        resolve: {
            instituicao: InstituicaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.instituicao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
