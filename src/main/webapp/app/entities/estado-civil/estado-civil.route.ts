import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { EstadoCivil } from 'app/shared/model/estado-civil.model';
import { EstadoCivilService } from './estado-civil.service';
import { EstadoCivilComponent } from './estado-civil.component';
import { EstadoCivilDetailComponent } from './estado-civil-detail.component';
import { EstadoCivilUpdateComponent } from './estado-civil-update.component';
import { EstadoCivilDeletePopupComponent } from './estado-civil-delete-dialog.component';
import { IEstadoCivil } from 'app/shared/model/estado-civil.model';

@Injectable({ providedIn: 'root' })
export class EstadoCivilResolve implements Resolve<IEstadoCivil> {
    constructor(private service: EstadoCivilService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<EstadoCivil> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<EstadoCivil>) => response.ok),
                map((estadoCivil: HttpResponse<EstadoCivil>) => estadoCivil.body)
            );
        }
        return of(new EstadoCivil());
    }
}

export const estadoCivilRoute: Routes = [
    {
        path: 'estado-civil',
        component: EstadoCivilComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.estadoCivil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'estado-civil/:id/view',
        component: EstadoCivilDetailComponent,
        resolve: {
            estadoCivil: EstadoCivilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.estadoCivil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'estado-civil/new',
        component: EstadoCivilUpdateComponent,
        resolve: {
            estadoCivil: EstadoCivilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.estadoCivil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'estado-civil/:id/edit',
        component: EstadoCivilUpdateComponent,
        resolve: {
            estadoCivil: EstadoCivilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.estadoCivil.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const estadoCivilPopupRoute: Routes = [
    {
        path: 'estado-civil/:id/delete',
        component: EstadoCivilDeletePopupComponent,
        resolve: {
            estadoCivil: EstadoCivilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.estadoCivil.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
