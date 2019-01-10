import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Materia } from 'app/shared/model/materia.model';
import { MateriaService } from './materia.service';
import { MateriaComponent } from './materia.component';
import { MateriaDetailComponent } from './materia-detail.component';
import { MateriaUpdateComponent } from './materia-update.component';
import { MateriaDeletePopupComponent } from './materia-delete-dialog.component';
import { IMateria } from 'app/shared/model/materia.model';

@Injectable({ providedIn: 'root' })
export class MateriaResolve implements Resolve<IMateria> {
    constructor(private service: MateriaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Materia> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Materia>) => response.ok),
                map((materia: HttpResponse<Materia>) => materia.body)
            );
        }
        return of(new Materia());
    }
}

export const materiaRoute: Routes = [
    {
        path: 'materia',
        component: MateriaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.materia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'materia/:id/view',
        component: MateriaDetailComponent,
        resolve: {
            materia: MateriaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.materia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'materia/new',
        component: MateriaUpdateComponent,
        resolve: {
            materia: MateriaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.materia.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'materia/:id/edit',
        component: MateriaUpdateComponent,
        resolve: {
            materia: MateriaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.materia.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const materiaPopupRoute: Routes = [
    {
        path: 'materia/:id/delete',
        component: MateriaDeletePopupComponent,
        resolve: {
            materia: MateriaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.materia.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
