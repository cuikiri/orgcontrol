import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    MotivoDiaNaoUtilComponent,
    MotivoDiaNaoUtilDetailComponent,
    MotivoDiaNaoUtilUpdateComponent,
    MotivoDiaNaoUtilDeletePopupComponent,
    MotivoDiaNaoUtilDeleteDialogComponent,
    motivoDiaNaoUtilRoute,
    motivoDiaNaoUtilPopupRoute
} from './';

const ENTITY_STATES = [...motivoDiaNaoUtilRoute, ...motivoDiaNaoUtilPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MotivoDiaNaoUtilComponent,
        MotivoDiaNaoUtilDetailComponent,
        MotivoDiaNaoUtilUpdateComponent,
        MotivoDiaNaoUtilDeleteDialogComponent,
        MotivoDiaNaoUtilDeletePopupComponent
    ],
    entryComponents: [
        MotivoDiaNaoUtilComponent,
        MotivoDiaNaoUtilUpdateComponent,
        MotivoDiaNaoUtilDeleteDialogComponent,
        MotivoDiaNaoUtilDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolMotivoDiaNaoUtilModule {}
