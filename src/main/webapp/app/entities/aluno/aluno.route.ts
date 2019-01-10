import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Aluno } from 'app/shared/model/aluno.model';
import { AlunoService } from './aluno.service';
import { AlunoComponent } from './aluno.component';
import { AlunoDetailComponent } from './aluno-detail.component';
import { AlunoUpdateComponent } from './aluno-update.component';
import { AlunoDeletePopupComponent } from './aluno-delete-dialog.component';
import { IAluno } from 'app/shared/model/aluno.model';

@Injectable({ providedIn: 'root' })
export class AlunoResolve implements Resolve<IAluno> {
    constructor(private service: AlunoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Aluno> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Aluno>) => response.ok),
                map((aluno: HttpResponse<Aluno>) => aluno.body)
            );
        }
        return of(new Aluno());
    }
}

export const alunoRoute: Routes = [
    {
        path: 'aluno',
        component: AlunoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orgcontrolApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aluno/:id/view',
        component: AlunoDetailComponent,
        resolve: {
            aluno: AlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aluno/new',
        component: AlunoUpdateComponent,
        resolve: {
            aluno: AlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'aluno/:id/edit',
        component: AlunoUpdateComponent,
        resolve: {
            aluno: AlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const alunoPopupRoute: Routes = [
    {
        path: 'aluno/:id/delete',
        component: AlunoDeletePopupComponent,
        resolve: {
            aluno: AlunoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orgcontrolApp.aluno.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
