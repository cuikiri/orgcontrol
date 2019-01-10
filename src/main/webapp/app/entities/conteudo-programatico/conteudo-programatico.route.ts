import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ConteudoProgramatico } from 'app/shared/model/conteudo-programatico.model';
import { ConteudoProgramaticoService } from './conteudo-programatico.service';
import { ConteudoProgramaticoComponent } from './conteudo-programatico.component';
import { ConteudoProgramaticoDetailComponent } from './conteudo-programatico-detail.component';
import { ConteudoProgramaticoUpdateComponent } from './conteudo-programatico-update.component';
import { ConteudoProgramaticoDeletePopupComponent } from './conteudo-programatico-delete-dialog.component';
import { IConteudoProgramatico } from 'app/shared/model/conteudo-programatico.model';

@Injectable({ providedIn: 'root' })
export class ConteudoProgramaticoResolve implements Resolve<IConteudoProgramatico> {
    constructor(private service: ConteudoProgramaticoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ConteudoProgramatico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ConteudoProgramatico>) => response.ok),
                map((conteudoProgramatico: HttpResponse<ConteudoProgramatico>) => conteudoProgramatico.body)
            );
        }
        return of(new ConteudoProgramatico());
    }
}

export const conteudoProgramaticoRoute: Routes = [
    {
        path: 'conteudo-programatico',
        component: ConteudoProgramaticoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.conteudoProgramatico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conteudo-programatico/:id/view',
        component: ConteudoProgramaticoDetailComponent,
        resolve: {
            conteudoProgramatico: ConteudoProgramaticoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conteudoProgramatico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conteudo-programatico/new',
        component: ConteudoProgramaticoUpdateComponent,
        resolve: {
            conteudoProgramatico: ConteudoProgramaticoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conteudoProgramatico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conteudo-programatico/:id/edit',
        component: ConteudoProgramaticoUpdateComponent,
        resolve: {
            conteudoProgramatico: ConteudoProgramaticoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conteudoProgramatico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const conteudoProgramaticoPopupRoute: Routes = [
    {
        path: 'conteudo-programatico/:id/delete',
        component: ConteudoProgramaticoDeletePopupComponent,
        resolve: {
            conteudoProgramatico: ConteudoProgramaticoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.conteudoProgramatico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
