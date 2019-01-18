import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    MatriculaComponent,
    MatriculaDetailComponent,
    MatriculaUpdateComponent,
    MatriculaDeletePopupComponent,
    MatriculaDeleteDialogComponent,
    matriculaRoute,
    matriculaPopupRoute
} from './';

const ENTITY_STATES = [...matriculaRoute, ...matriculaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MatriculaComponent,
        MatriculaDetailComponent,
        MatriculaUpdateComponent,
        MatriculaDeleteDialogComponent,
        MatriculaDeletePopupComponent
    ],
    entryComponents: [MatriculaComponent, MatriculaUpdateComponent, MatriculaDeleteDialogComponent, MatriculaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolMatriculaModule {}
