import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Matricula } from 'app/shared/model/matricula.model';
import { MatriculaService } from './matricula.service';
import { MatriculaComponent } from './matricula.component';
import { MatriculaDetailComponent } from './matricula-detail.component';
import { MatriculaUpdateComponent } from './matricula-update.component';
import { MatriculaDeletePopupComponent } from './matricula-delete-dialog.component';
import { IMatricula } from 'app/shared/model/matricula.model';

@Injectable({ providedIn: 'root' })
export class MatriculaResolve implements Resolve<IMatricula> {
    constructor(private service: MatriculaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Matricula> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Matricula>) => response.ok),
                map((matricula: HttpResponse<Matricula>) => matricula.body)
            );
        }
        return of(new Matricula());
    }
}

export const matriculaRoute: Routes = [
    {
        path: 'matricula',
        component: MatriculaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.matricula.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matricula/:id/view',
        component: MatriculaDetailComponent,
        resolve: {
            matricula: MatriculaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.matricula.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matricula/new',
        component: MatriculaUpdateComponent,
        resolve: {
            matricula: MatriculaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.matricula.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'matricula/:id/edit',
        component: MatriculaUpdateComponent,
        resolve: {
            matricula: MatriculaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.matricula.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const matriculaPopupRoute: Routes = [
    {
        path: 'matricula/:id/delete',
        component: MatriculaDeletePopupComponent,
        resolve: {
            matricula: MatriculaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.matricula.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
