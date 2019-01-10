import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    PeriodoAtividadeComponent,
    PeriodoAtividadeDetailComponent,
    PeriodoAtividadeUpdateComponent,
    PeriodoAtividadeDeletePopupComponent,
    PeriodoAtividadeDeleteDialogComponent,
    periodoAtividadeRoute,
    periodoAtividadePopupRoute
} from './';

const ENTITY_STATES = [...periodoAtividadeRoute, ...periodoAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PeriodoAtividadeComponent,
        PeriodoAtividadeDetailComponent,
        PeriodoAtividadeUpdateComponent,
        PeriodoAtividadeDeleteDialogComponent,
        PeriodoAtividadeDeletePopupComponent
    ],
    entryComponents: [
        PeriodoAtividadeComponent,
        PeriodoAtividadeUpdateComponent,
        PeriodoAtividadeDeleteDialogComponent,
        PeriodoAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolPeriodoAtividadeModule {}
