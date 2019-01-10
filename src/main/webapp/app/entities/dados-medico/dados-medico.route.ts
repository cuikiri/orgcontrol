import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DadosMedico } from 'app/shared/model/dados-medico.model';
import { DadosMedicoService } from './dados-medico.service';
import { DadosMedicoComponent } from './dados-medico.component';
import { DadosMedicoDetailComponent } from './dados-medico-detail.component';
import { DadosMedicoUpdateComponent } from './dados-medico-update.component';
import { DadosMedicoDeletePopupComponent } from './dados-medico-delete-dialog.component';
import { IDadosMedico } from 'app/shared/model/dados-medico.model';

@Injectable({ providedIn: 'root' })
export class DadosMedicoResolve implements Resolve<IDadosMedico> {
    constructor(private service: DadosMedicoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DadosMedico> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DadosMedico>) => response.ok),
                map((dadosMedico: HttpResponse<DadosMedico>) => dadosMedico.body)
            );
        }
        return of(new DadosMedico());
    }
}

export const dadosMedicoRoute: Routes = [
    {
        path: 'dados-medico',
        component: DadosMedicoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.dadosMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dados-medico/:id/view',
        component: DadosMedicoDetailComponent,
        resolve: {
            dadosMedico: DadosMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dadosMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dados-medico/new',
        component: DadosMedicoUpdateComponent,
        resolve: {
            dadosMedico: DadosMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dadosMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'dados-medico/:id/edit',
        component: DadosMedicoUpdateComponent,
        resolve: {
            dadosMedico: DadosMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dadosMedico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dadosMedicoPopupRoute: Routes = [
    {
        path: 'dados-medico/:id/delete',
        component: DadosMedicoDeletePopupComponent,
        resolve: {
            dadosMedico: DadosMedicoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.dadosMedico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
