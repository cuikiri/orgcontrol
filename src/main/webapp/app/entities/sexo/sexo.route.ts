import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Sexo } from 'app/shared/model/sexo.model';
import { SexoService } from './sexo.service';
import { SexoComponent } from './sexo.component';
import { SexoDetailComponent } from './sexo-detail.component';
import { SexoUpdateComponent } from './sexo-update.component';
import { SexoDeletePopupComponent } from './sexo-delete-dialog.component';
import { ISexo } from 'app/shared/model/sexo.model';

@Injectable({ providedIn: 'root' })
export class SexoResolve implements Resolve<ISexo> {
    constructor(private service: SexoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Sexo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Sexo>) => response.ok),
                map((sexo: HttpResponse<Sexo>) => sexo.body)
            );
        }
        return of(new Sexo());
    }
}

export const sexoRoute: Routes = [
    {
        path: 'sexo',
        component: SexoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.sexo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sexo/:id/view',
        component: SexoDetailComponent,
        resolve: {
            sexo: SexoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.sexo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sexo/new',
        component: SexoUpdateComponent,
        resolve: {
            sexo: SexoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.sexo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sexo/:id/edit',
        component: SexoUpdateComponent,
        resolve: {
            sexo: SexoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.sexo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sexoPopupRoute: Routes = [
    {
        path: 'sexo/:id/delete',
        component: SexoDeletePopupComponent,
        resolve: {
            sexo: SexoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.sexo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
