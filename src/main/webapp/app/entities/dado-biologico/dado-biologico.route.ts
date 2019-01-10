import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DadoBiologico } from 'app/shared/model/dado-biologico.model';
import { DadoBiologicoService } from './dado-biologico.service';
import { DadoBiologicoComponent } from './dado-biologico.component';
import { DadoBiologicoDetailComponent } from './dado-biologico-detail.component';
import { DadoBiologicoUpdateComponent } from './dado-biologico-update.component';
import { DadoBiologicoDeletePopupComponent } from './dado-biologico-delete-dialog.component';
import { IDadoBiologico } from 'app/shared/model/dado-biologico.model';

@Injectable({ providedIn: 'root' })
export class DadoBiologicoResolve implements Resolve<IDadoBiologico> {
    constructor(private service: DadoBiologicoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DadoBiologico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DadoBiologico>) => response.ok),
                map((dadoBiologico: HttpResponse<DadoBiologico>) => dadoBiologico.body)
            );
        }
        return of(new DadoBiologico());
    }
}

export const dadoBiologicoRoute: Routes = [
    {
        path: 'dado-biologico',
        component: DadoBiologicoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.dadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dado-biologico/:id/view',
        component: DadoBiologicoDetailComponent,
        resolve: {
            dadoBiologico: DadoBiologicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dado-biologico/new',
        component: DadoBiologicoUpdateComponent,
        resolve: {
            dadoBiologico: DadoBiologicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dado-biologico/:id/edit',
        component: DadoBiologicoUpdateComponent,
        resolve: {
            dadoBiologico: DadoBiologicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dadoBiologicoPopupRoute: Routes = [
    {
        path: 'dado-biologico/:id/delete',
        component: DadoBiologicoDeletePopupComponent,
        resolve: {
            dadoBiologico: DadoBiologicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dadoBiologico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
