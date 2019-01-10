import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MotivoDiaNaoUtil } from 'app/shared/model/motivo-dia-nao-util.model';
import { MotivoDiaNaoUtilService } from './motivo-dia-nao-util.service';
import { MotivoDiaNaoUtilComponent } from './motivo-dia-nao-util.component';
import { MotivoDiaNaoUtilDetailComponent } from './motivo-dia-nao-util-detail.component';
import { MotivoDiaNaoUtilUpdateComponent } from './motivo-dia-nao-util-update.component';
import { MotivoDiaNaoUtilDeletePopupComponent } from './motivo-dia-nao-util-delete-dialog.component';
import { IMotivoDiaNaoUtil } from 'app/shared/model/motivo-dia-nao-util.model';

@Injectable({ providedIn: 'root' })
export class MotivoDiaNaoUtilResolve implements Resolve<IMotivoDiaNaoUtil> {
    constructor(private service: MotivoDiaNaoUtilService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<MotivoDiaNaoUtil> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<MotivoDiaNaoUtil>) => response.ok),
                map((motivoDiaNaoUtil: HttpResponse<MotivoDiaNaoUtil>) => motivoDiaNaoUtil.body)
            );
        }
        return of(new MotivoDiaNaoUtil());
    }
}

export const motivoDiaNaoUtilRoute: Routes = [
    {
        path: 'motivo-dia-nao-util',
        component: MotivoDiaNaoUtilComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.motivoDiaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'motivo-dia-nao-util/:id/view',
        component: MotivoDiaNaoUtilDetailComponent,
        resolve: {
            motivoDiaNaoUtil: MotivoDiaNaoUtilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.motivoDiaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'motivo-dia-nao-util/new',
        component: MotivoDiaNaoUtilUpdateComponent,
        resolve: {
            motivoDiaNaoUtil: MotivoDiaNaoUtilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.motivoDiaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'motivo-dia-nao-util/:id/edit',
        component: MotivoDiaNaoUtilUpdateComponent,
        resolve: {
            motivoDiaNaoUtil: MotivoDiaNaoUtilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.motivoDiaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const motivoDiaNaoUtilPopupRoute: Routes = [
    {
        path: 'motivo-dia-nao-util/:id/delete',
        component: MotivoDiaNaoUtilDeletePopupComponent,
        resolve: {
            motivoDiaNaoUtil: MotivoDiaNaoUtilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.motivoDiaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
