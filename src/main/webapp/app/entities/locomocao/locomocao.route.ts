import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Locomocao } from 'app/shared/model/locomocao.model';
import { LocomocaoService } from './locomocao.service';
import { LocomocaoComponent } from './locomocao.component';
import { LocomocaoDetailComponent } from './locomocao-detail.component';
import { LocomocaoUpdateComponent } from './locomocao-update.component';
import { LocomocaoDeletePopupComponent } from './locomocao-delete-dialog.component';
import { ILocomocao } from 'app/shared/model/locomocao.model';

@Injectable({ providedIn: 'root' })
export class LocomocaoResolve implements Resolve<ILocomocao> {
    constructor(private service: LocomocaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Locomocao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Locomocao>) => response.ok),
                map((locomocao: HttpResponse<Locomocao>) => locomocao.body)
            );
        }
        return of(new Locomocao());
    }
}

export const locomocaoRoute: Routes = [
    {
        path: 'locomocao',
        component: LocomocaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.locomocao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'locomocao/:id/view',
        component: LocomocaoDetailComponent,
        resolve: {
            locomocao: LocomocaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.locomocao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'locomocao/new',
        component: LocomocaoUpdateComponent,
        resolve: {
            locomocao: LocomocaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.locomocao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'locomocao/:id/edit',
        component: LocomocaoUpdateComponent,
        resolve: {
            locomocao: LocomocaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.locomocao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const locomocaoPopupRoute: Routes = [
    {
        path: 'locomocao/:id/delete',
        component: LocomocaoDeletePopupComponent,
        resolve: {
            locomocao: LocomocaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.locomocao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
