import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ExameMedicoComponent,
    ExameMedicoDetailComponent,
    ExameMedicoUpdateComponent,
    ExameMedicoDeletePopupComponent,
    ExameMedicoDeleteDialogComponent,
    exameMedicoRoute,
    exameMedicoPopupRoute
} from './';

const ENTITY_STATES = [...exameMedicoRoute, ...exameMedicoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ExameMedicoComponent,
        ExameMedicoDetailComponent,
        ExameMedicoUpdateComponent,
        ExameMedicoDeleteDialogComponent,
        ExameMedicoDeletePopupComponent
    ],
    entryComponents: [ExameMedicoComponent, ExameMedicoUpdateComponent, ExameMedicoDeleteDialogComponent, ExameMedicoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolExameMedicoModule {}
