import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    PeriodoSemanaComponent,
    PeriodoSemanaDetailComponent,
    PeriodoSemanaUpdateComponent,
    PeriodoSemanaDeletePopupComponent,
    PeriodoSemanaDeleteDialogComponent,
    periodoSemanaRoute,
    periodoSemanaPopupRoute
} from './';

const ENTITY_STATES = [...periodoSemanaRoute, ...periodoSemanaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PeriodoSemanaComponent,
        PeriodoSemanaDetailComponent,
        PeriodoSemanaUpdateComponent,
        PeriodoSemanaDeleteDialogComponent,
        PeriodoSemanaDeletePopupComponent
    ],
    entryComponents: [
        PeriodoSemanaComponent,
        PeriodoSemanaUpdateComponent,
        PeriodoSemanaDeleteDialogComponent,
        PeriodoSemanaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolPeriodoSemanaModule {}
