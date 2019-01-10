import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    CalendarioInstituicaoComponent,
    CalendarioInstituicaoDetailComponent,
    CalendarioInstituicaoUpdateComponent,
    CalendarioInstituicaoDeletePopupComponent,
    CalendarioInstituicaoDeleteDialogComponent,
    calendarioInstituicaoRoute,
    calendarioInstituicaoPopupRoute
} from './';

const ENTITY_STATES = [...calendarioInstituicaoRoute, ...calendarioInstituicaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CalendarioInstituicaoComponent,
        CalendarioInstituicaoDetailComponent,
        CalendarioInstituicaoUpdateComponent,
        CalendarioInstituicaoDeleteDialogComponent,
        CalendarioInstituicaoDeletePopupComponent
    ],
    entryComponents: [
        CalendarioInstituicaoComponent,
        CalendarioInstituicaoUpdateComponent,
        CalendarioInstituicaoDeleteDialogComponent,
        CalendarioInstituicaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolCalendarioInstituicaoModule {}
