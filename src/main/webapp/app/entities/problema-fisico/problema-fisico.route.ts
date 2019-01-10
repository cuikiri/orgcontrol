import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProblemaFisico } from 'app/shared/model/problema-fisico.model';
import { ProblemaFisicoService } from './problema-fisico.service';
import { ProblemaFisicoComponent } from './problema-fisico.component';
import { ProblemaFisicoDetailComponent } from './problema-fisico-detail.component';
import { ProblemaFisicoUpdateComponent } from './problema-fisico-update.component';
import { ProblemaFisicoDeletePopupComponent } from './problema-fisico-delete-dialog.component';
import { IProblemaFisico } from 'app/shared/model/problema-fisico.model';

@Injectable({ providedIn: 'root' })
export class ProblemaFisicoResolve implements Resolve<IProblemaFisico> {
    constructor(private service: ProblemaFisicoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProblemaFisico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProblemaFisico>) => response.ok),
                map((problemaFisico: HttpResponse<ProblemaFisico>) => problemaFisico.body)
            );
        }
        return of(new ProblemaFisico());
    }
}

export const problemaFisicoRoute: Routes = [
    {
        path: 'problema-fisico',
        component: ProblemaFisicoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.problemaFisico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'problema-fisico/:id/view',
        component: ProblemaFisicoDetailComponent,
        resolve: {
            problemaFisico: ProblemaFisicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.problemaFisico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'problema-fisico/new',
        component: ProblemaFisicoUpdateComponent,
        resolve: {
            problemaFisico: ProblemaFisicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.problemaFisico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'problema-fisico/:id/edit',
        component: ProblemaFisicoUpdateComponent,
        resolve: {
            problemaFisico: ProblemaFisicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.problemaFisico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const problemaFisicoPopupRoute: Routes = [
    {
        path: 'problema-fisico/:id/delete',
        component: ProblemaFisicoDeletePopupComponent,
        resolve: {
            problemaFisico: ProblemaFisicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.problemaFisico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
