import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TipoBiotipo } from 'app/shared/model/tipo-biotipo.model';
import { TipoBiotipoService } from './tipo-biotipo.service';
import { TipoBiotipoComponent } from './tipo-biotipo.component';
import { TipoBiotipoDetailComponent } from './tipo-biotipo-detail.component';
import { TipoBiotipoUpdateComponent } from './tipo-biotipo-update.component';
import { TipoBiotipoDeletePopupComponent } from './tipo-biotipo-delete-dialog.component';
import { ITipoBiotipo } from 'app/shared/model/tipo-biotipo.model';

@Injectable({ providedIn: 'root' })
export class TipoBiotipoResolve implements Resolve<ITipoBiotipo> {
    constructor(private service: TipoBiotipoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TipoBiotipo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TipoBiotipo>) => response.ok),
                map((tipoBiotipo: HttpResponse<TipoBiotipo>) => tipoBiotipo.body)
            );
        }
        return of(new TipoBiotipo());
    }
}

export const tipoBiotipoRoute: Routes = [
    {
        path: 'tipo-biotipo',
        component: TipoBiotipoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.tipoBiotipo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-biotipo/:id/view',
        component: TipoBiotipoDetailComponent,
        resolve: {
            tipoBiotipo: TipoBiotipoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoBiotipo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-biotipo/new',
        component: TipoBiotipoUpdateComponent,
        resolve: {
            tipoBiotipo: TipoBiotipoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoBiotipo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tipo-biotipo/:id/edit',
        component: TipoBiotipoUpdateComponent,
        resolve: {
            tipoBiotipo: TipoBiotipoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoBiotipo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tipoBiotipoPopupRoute: Routes = [
    {
        path: 'tipo-biotipo/:id/delete',
        component: TipoBiotipoDeletePopupComponent,
        resolve: {
            tipoBiotipo: TipoBiotipoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.tipoBiotipo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
