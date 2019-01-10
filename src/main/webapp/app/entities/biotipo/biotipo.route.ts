import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Biotipo } from 'app/shared/model/biotipo.model';
import { BiotipoService } from './biotipo.service';
import { BiotipoComponent } from './biotipo.component';
import { BiotipoDetailComponent } from './biotipo-detail.component';
import { BiotipoUpdateComponent } from './biotipo-update.component';
import { BiotipoDeletePopupComponent } from './biotipo-delete-dialog.component';
import { IBiotipo } from 'app/shared/model/biotipo.model';

@Injectable({ providedIn: 'root' })
export class BiotipoResolve implements Resolve<IBiotipo> {
    constructor(private service: BiotipoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Biotipo> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Biotipo>) => response.ok),
                map((biotipo: HttpResponse<Biotipo>) => biotipo.body)
            );
        }
        return of(new Biotipo());
    }
}

export const biotipoRoute: Routes = [
    {
        path: 'biotipo',
        component: BiotipoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.biotipo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'biotipo/:id/view',
        component: BiotipoDetailComponent,
        resolve: {
            biotipo: BiotipoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.biotipo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'biotipo/new',
        component: BiotipoUpdateComponent,
        resolve: {
            biotipo: BiotipoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.biotipo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'biotipo/:id/edit',
        component: BiotipoUpdateComponent,
        resolve: {
            biotipo: BiotipoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.biotipo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const biotipoPopupRoute: Routes = [
    {
        path: 'biotipo/:id/delete',
        component: BiotipoDeletePopupComponent,
        resolve: {
            biotipo: BiotipoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.biotipo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
