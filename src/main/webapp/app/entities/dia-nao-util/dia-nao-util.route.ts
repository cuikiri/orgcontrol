import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DiaNaoUtil } from 'app/shared/model/dia-nao-util.model';
import { DiaNaoUtilService } from './dia-nao-util.service';
import { DiaNaoUtilComponent } from './dia-nao-util.component';
import { DiaNaoUtilDetailComponent } from './dia-nao-util-detail.component';
import { DiaNaoUtilUpdateComponent } from './dia-nao-util-update.component';
import { DiaNaoUtilDeletePopupComponent } from './dia-nao-util-delete-dialog.component';
import { IDiaNaoUtil } from 'app/shared/model/dia-nao-util.model';

@Injectable({ providedIn: 'root' })
export class DiaNaoUtilResolve implements Resolve<IDiaNaoUtil> {
    constructor(private service: DiaNaoUtilService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DiaNaoUtil> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DiaNaoUtil>) => response.ok),
                map((diaNaoUtil: HttpResponse<DiaNaoUtil>) => diaNaoUtil.body)
            );
        }
        return of(new DiaNaoUtil());
    }
}

export const diaNaoUtilRoute: Routes = [
    {
        path: 'dia-nao-util',
        component: DiaNaoUtilComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.diaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dia-nao-util/:id/view',
        component: DiaNaoUtilDetailComponent,
        resolve: {
            diaNaoUtil: DiaNaoUtilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dia-nao-util/new',
        component: DiaNaoUtilUpdateComponent,
        resolve: {
            diaNaoUtil: DiaNaoUtilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dia-nao-util/:id/edit',
        component: DiaNaoUtilUpdateComponent,
        resolve: {
            diaNaoUtil: DiaNaoUtilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const diaNaoUtilPopupRoute: Routes = [
    {
        path: 'dia-nao-util/:id/delete',
        component: DiaNaoUtilDeletePopupComponent,
        resolve: {
            diaNaoUtil: DiaNaoUtilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.diaNaoUtil.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
