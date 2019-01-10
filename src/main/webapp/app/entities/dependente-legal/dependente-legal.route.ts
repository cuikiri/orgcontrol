import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DependenteLegal } from 'app/shared/model/dependente-legal.model';
import { DependenteLegalService } from './dependente-legal.service';
import { DependenteLegalComponent } from './dependente-legal.component';
import { DependenteLegalDetailComponent } from './dependente-legal-detail.component';
import { DependenteLegalUpdateComponent } from './dependente-legal-update.component';
import { DependenteLegalDeletePopupComponent } from './dependente-legal-delete-dialog.component';
import { IDependenteLegal } from 'app/shared/model/dependente-legal.model';

@Injectable({ providedIn: 'root' })
export class DependenteLegalResolve implements Resolve<IDependenteLegal> {
    constructor(private service: DependenteLegalService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DependenteLegal> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DependenteLegal>) => response.ok),
                map((dependenteLegal: HttpResponse<DependenteLegal>) => dependenteLegal.body)
            );
        }
        return of(new DependenteLegal());
    }
}

export const dependenteLegalRoute: Routes = [
    {
        path: 'dependente-legal',
        component: DependenteLegalComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.dependenteLegal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dependente-legal/:id/view',
        component: DependenteLegalDetailComponent,
        resolve: {
            dependenteLegal: DependenteLegalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dependenteLegal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dependente-legal/new',
        component: DependenteLegalUpdateComponent,
        resolve: {
            dependenteLegal: DependenteLegalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dependenteLegal.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dependente-legal/:id/edit',
        component: DependenteLegalUpdateComponent,
        resolve: {
            dependenteLegal: DependenteLegalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dependenteLegal.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dependenteLegalPopupRoute: Routes = [
    {
        path: 'dependente-legal/:id/delete',
        component: DependenteLegalDeletePopupComponent,
        resolve: {
            dependenteLegal: DependenteLegalResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dependenteLegal.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
