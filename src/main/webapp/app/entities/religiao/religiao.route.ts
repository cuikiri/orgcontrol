import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Religiao } from 'app/shared/model/religiao.model';
import { ReligiaoService } from './religiao.service';
import { ReligiaoComponent } from './religiao.component';
import { ReligiaoDetailComponent } from './religiao-detail.component';
import { ReligiaoUpdateComponent } from './religiao-update.component';
import { ReligiaoDeletePopupComponent } from './religiao-delete-dialog.component';
import { IReligiao } from 'app/shared/model/religiao.model';

@Injectable({ providedIn: 'root' })
export class ReligiaoResolve implements Resolve<IReligiao> {
    constructor(private service: ReligiaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Religiao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Religiao>) => response.ok),
                map((religiao: HttpResponse<Religiao>) => religiao.body)
            );
        }
        return of(new Religiao());
    }
}

export const religiaoRoute: Routes = [
    {
        path: 'religiao',
        component: ReligiaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.religiao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'religiao/:id/view',
        component: ReligiaoDetailComponent,
        resolve: {
            religiao: ReligiaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.religiao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'religiao/new',
        component: ReligiaoUpdateComponent,
        resolve: {
            religiao: ReligiaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.religiao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'religiao/:id/edit',
        component: ReligiaoUpdateComponent,
        resolve: {
            religiao: ReligiaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.religiao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const religiaoPopupRoute: Routes = [
    {
        path: 'religiao/:id/delete',
        component: ReligiaoDeletePopupComponent,
        resolve: {
            religiao: ReligiaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.religiao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
