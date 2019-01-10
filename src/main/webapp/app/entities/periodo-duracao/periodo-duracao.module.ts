import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    PeriodoDuracaoComponent,
    PeriodoDuracaoDetailComponent,
    PeriodoDuracaoUpdateComponent,
    PeriodoDuracaoDeletePopupComponent,
    PeriodoDuracaoDeleteDialogComponent,
    periodoDuracaoRoute,
    periodoDuracaoPopupRoute
} from './';

const ENTITY_STATES = [...periodoDuracaoRoute, ...periodoDuracaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PeriodoDuracaoComponent,
        PeriodoDuracaoDetailComponent,
        PeriodoDuracaoUpdateComponent,
        PeriodoDuracaoDeleteDialogComponent,
        PeriodoDuracaoDeletePopupComponent
    ],
    entryComponents: [
        PeriodoDuracaoComponent,
        PeriodoDuracaoUpdateComponent,
        PeriodoDuracaoDeleteDialogComponent,
        PeriodoDuracaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolPeriodoDuracaoModule {}
