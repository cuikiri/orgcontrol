import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HorarioMateria } from 'app/shared/model/horario-materia.model';
import { HorarioMateriaService } from './horario-materia.service';
import { HorarioMateriaComponent } from './horario-materia.component';
import { HorarioMateriaDetailComponent } from './horario-materia-detail.component';
import { HorarioMateriaUpdateComponent } from './horario-materia-update.component';
import { HorarioMateriaDeletePopupComponent } from './horario-materia-delete-dialog.component';
import { IHorarioMateria } from 'app/shared/model/horario-materia.model';

@Injectable({ providedIn: 'root' })
export class HorarioMateriaResolve implements Resolve<IHorarioMateria> {
    constructor(private service: HorarioMateriaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<HorarioMateria> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HorarioMateria>) => response.ok),
                map((horarioMateria: HttpResponse<HorarioMateria>) => horarioMateria.body)
            );
        }
        return of(new HorarioMateria());
    }
}

export const horarioMateriaRoute: Routes = [
    {
        path: 'horario-materia',
        component: HorarioMateriaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.horarioMateria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'horario-materia/:id/view',
        component: HorarioMateriaDetailComponent,
        resolve: {
            horarioMateria: HorarioMateriaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.horarioMateria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'horario-materia/new',
        component: HorarioMateriaUpdateComponent,
        resolve: {
            horarioMateria: HorarioMateriaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.horarioMateria.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'horario-materia/:id/edit',
        component: HorarioMateriaUpdateComponent,
        resolve: {
            horarioMateria: HorarioMateriaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.horarioMateria.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const horarioMateriaPopupRoute: Routes = [
    {
        path: 'horario-materia/:id/delete',
        component: HorarioMateriaDeletePopupComponent,
        resolve: {
            horarioMateria: HorarioMateriaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.horarioMateria.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
