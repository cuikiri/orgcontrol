import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Aviso } from 'app/shared/model/aviso.model';
import { AvisoService } from './aviso.service';
import { AvisoComponent } from './aviso.component';
import { AvisoDetailComponent } from './aviso-detail.component';
import { AvisoUpdateComponent } from './aviso-update.component';
import { AvisoDeletePopupComponent } from './aviso-delete-dialog.component';
import { IAviso } from 'app/shared/model/aviso.model';

@Injectable({ providedIn: 'root' })
export class AvisoResolve implements Resolve<IAviso> {
    constructor(private service: AvisoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Aviso> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Aviso>) => response.ok),
                map((aviso: HttpResponse<Aviso>) => aviso.body)
            );
        }
        return of(new Aviso());
    }
}

export const avisoRoute: Routes = [
    {
        path: 'aviso',
        component: AvisoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.aviso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aviso/:id/view',
        component: AvisoDetailComponent,
        resolve: {
            aviso: AvisoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.aviso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aviso/new',
        component: AvisoUpdateComponent,
        resolve: {
            aviso: AvisoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.aviso.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aviso/:id/edit',
        component: AvisoUpdateComponent,
        resolve: {
            aviso: AvisoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.aviso.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const avisoPopupRoute: Routes = [
    {
        path: 'aviso/:id/delete',
        component: AvisoDeletePopupComponent,
        resolve: {
            aviso: AvisoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.aviso.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
