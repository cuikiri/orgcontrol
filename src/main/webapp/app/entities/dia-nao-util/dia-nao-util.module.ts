import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    DiaNaoUtilComponent,
    DiaNaoUtilDetailComponent,
    DiaNaoUtilUpdateComponent,
    DiaNaoUtilDeletePopupComponent,
    DiaNaoUtilDeleteDialogComponent,
    diaNaoUtilRoute,
    diaNaoUtilPopupRoute
} from './';

const ENTITY_STATES = [...diaNaoUtilRoute, ...diaNaoUtilPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DiaNaoUtilComponent,
        DiaNaoUtilDetailComponent,
        DiaNaoUtilUpdateComponent,
        DiaNaoUtilDeleteDialogComponent,
        DiaNaoUtilDeletePopupComponent
    ],
    entryComponents: [DiaNaoUtilComponent, DiaNaoUtilUpdateComponent, DiaNaoUtilDeleteDialogComponent, DiaNaoUtilDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolDiaNaoUtilModule {}
