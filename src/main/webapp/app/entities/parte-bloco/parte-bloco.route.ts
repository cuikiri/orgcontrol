import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ParteBloco } from 'app/shared/model/parte-bloco.model';
import { ParteBlocoService } from './parte-bloco.service';
import { ParteBlocoComponent } from './parte-bloco.component';
import { ParteBlocoDetailComponent } from './parte-bloco-detail.component';
import { ParteBlocoUpdateComponent } from './parte-bloco-update.component';
import { ParteBlocoDeletePopupComponent } from './parte-bloco-delete-dialog.component';
import { IParteBloco } from 'app/shared/model/parte-bloco.model';

@Injectable({ providedIn: 'root' })
export class ParteBlocoResolve implements Resolve<IParteBloco> {
    constructor(private service: ParteBlocoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ParteBloco> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ParteBloco>) => response.ok),
                map((parteBloco: HttpResponse<ParteBloco>) => parteBloco.body)
            );
        }
        return of(new ParteBloco());
    }
}

export const parteBlocoRoute: Routes = [
    {
        path: 'parte-bloco',
        component: ParteBlocoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.parteBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'parte-bloco/:id/view',
        component: ParteBlocoDetailComponent,
        resolve: {
            parteBloco: ParteBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.parteBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'parte-bloco/new',
        component: ParteBlocoUpdateComponent,
        resolve: {
            parteBloco: ParteBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.parteBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'parte-bloco/:id/edit',
        component: ParteBlocoUpdateComponent,
        resolve: {
            parteBloco: ParteBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.parteBloco.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const parteBlocoPopupRoute: Routes = [
    {
        path: 'parte-bloco/:id/delete',
        component: ParteBlocoDeletePopupComponent,
        resolve: {
            parteBloco: ParteBlocoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.parteBloco.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
