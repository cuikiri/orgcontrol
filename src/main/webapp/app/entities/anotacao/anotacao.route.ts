import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Anotacao } from 'app/shared/model/anotacao.model';
import { AnotacaoService } from './anotacao.service';
import { AnotacaoComponent } from './anotacao.component';
import { AnotacaoDetailComponent } from './anotacao-detail.component';
import { AnotacaoUpdateComponent } from './anotacao-update.component';
import { AnotacaoDeletePopupComponent } from './anotacao-delete-dialog.component';
import { IAnotacao } from 'app/shared/model/anotacao.model';

@Injectable({ providedIn: 'root' })
export class AnotacaoResolve implements Resolve<IAnotacao> {
    constructor(private service: AnotacaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Anotacao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Anotacao>) => response.ok),
                map((anotacao: HttpResponse<Anotacao>) => anotacao.body)
            );
        }
        return of(new Anotacao());
    }
}

export const anotacaoRoute: Routes = [
    {
        path: 'anotacao',
        component: AnotacaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.anotacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'anotacao/:id/view',
        component: AnotacaoDetailComponent,
        resolve: {
            anotacao: AnotacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.anotacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'anotacao/new',
        component: AnotacaoUpdateComponent,
        resolve: {
            anotacao: AnotacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.anotacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'anotacao/:id/edit',
        component: AnotacaoUpdateComponent,
        resolve: {
            anotacao: AnotacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.anotacao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const anotacaoPopupRoute: Routes = [
    {
        path: 'anotacao/:id/delete',
        component: AnotacaoDeletePopupComponent,
        resolve: {
            anotacao: AnotacaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.anotacao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
