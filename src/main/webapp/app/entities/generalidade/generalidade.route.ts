import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Generalidade } from 'app/shared/model/generalidade.model';
import { GeneralidadeService } from './generalidade.service';
import { GeneralidadeComponent } from './generalidade.component';
import { GeneralidadeDetailComponent } from './generalidade-detail.component';
import { GeneralidadeUpdateComponent } from './generalidade-update.component';
import { GeneralidadeDeletePopupComponent } from './generalidade-delete-dialog.component';
import { IGeneralidade } from 'app/shared/model/generalidade.model';

@Injectable({ providedIn: 'root' })
export class GeneralidadeResolve implements Resolve<IGeneralidade> {
    constructor(private service: GeneralidadeService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Generalidade> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Generalidade>) => response.ok),
                map((generalidade: HttpResponse<Generalidade>) => generalidade.body)
            );
        }
        return of(new Generalidade());
    }
}

export const generalidadeRoute: Routes = [
    {
        path: 'generalidade',
        component: GeneralidadeComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.generalidade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'generalidade/:id/view',
        component: GeneralidadeDetailComponent,
        resolve: {
            generalidade: GeneralidadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.generalidade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'generalidade/new',
        component: GeneralidadeUpdateComponent,
        resolve: {
            generalidade: GeneralidadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.generalidade.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'generalidade/:id/edit',
        component: GeneralidadeUpdateComponent,
        resolve: {
            generalidade: GeneralidadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.generalidade.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const generalidadePopupRoute: Routes = [
    {
        path: 'generalidade/:id/delete',
        component: GeneralidadeDeletePopupComponent,
        resolve: {
            generalidade: GeneralidadeResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.generalidade.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
