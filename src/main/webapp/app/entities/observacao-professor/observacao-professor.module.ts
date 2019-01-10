import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ObservacaoProfessorComponent,
    ObservacaoProfessorDetailComponent,
    ObservacaoProfessorUpdateComponent,
    ObservacaoProfessorDeletePopupComponent,
    ObservacaoProfessorDeleteDialogComponent,
    observacaoProfessorRoute,
    observacaoProfessorPopupRoute
} from './';

const ENTITY_STATES = [...observacaoProfessorRoute, ...observacaoProfessorPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ObservacaoProfessorComponent,
        ObservacaoProfessorDetailComponent,
        ObservacaoProfessorUpdateComponent,
        ObservacaoProfessorDeleteDialogComponent,
        ObservacaoProfessorDeletePopupComponent
    ],
    entryComponents: [
        ObservacaoProfessorComponent,
        ObservacaoProfessorUpdateComponent,
        ObservacaoProfessorDeleteDialogComponent,
        ObservacaoProfessorDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolObservacaoProfessorModule {}
