import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Diario } from 'app/shared/model/diario.model';
import { DiarioService } from './diario.service';
import { DiarioComponent } from './diario.component';
import { DiarioDetailComponent } from './diario-detail.component';
import { DiarioUpdateComponent } from './diario-update.component';
import { DiarioDeletePopupComponent } from './diario-delete-dialog.component';
import { IDiario } from 'app/shared/model/diario.model';

@Injectable({ providedIn: 'root' })
export class DiarioResolve implements Resolve<IDiario> {
    constructor(private service: DiarioService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Diario> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Diario>) => response.ok),
                map((diario: HttpResponse<Diario>) => diario.body)
            );
        }
        return of(new Diario());
    }
}

export const diarioRoute: Routes = [
    {
        path: 'diario',
        component: DiarioComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.diario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'diario/:id/view',
        component: DiarioDetailComponent,
        resolve: {
            diario: DiarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'diario/new',
        component: DiarioUpdateComponent,
        resolve: {
            diario: DiarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diario.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'diario/:id/edit',
        component: DiarioUpdateComponent,
        resolve: {
            diario: DiarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diario.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const diarioPopupRoute: Routes = [
    {
        path: 'diario/:id/delete',
        component: DiarioDeletePopupComponent,
        resolve: {
            diario: DiarioResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diario.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
