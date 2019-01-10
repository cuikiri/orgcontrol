import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    FechamentoBimestreComponent,
    FechamentoBimestreDetailComponent,
    FechamentoBimestreUpdateComponent,
    FechamentoBimestreDeletePopupComponent,
    FechamentoBimestreDeleteDialogComponent,
    fechamentoBimestreRoute,
    fechamentoBimestrePopupRoute
} from './';

const ENTITY_STATES = [...fechamentoBimestreRoute, ...fechamentoBimestrePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FechamentoBimestreComponent,
        FechamentoBimestreDetailComponent,
        FechamentoBimestreUpdateComponent,
        FechamentoBimestreDeleteDialogComponent,
        FechamentoBimestreDeletePopupComponent
    ],
    entryComponents: [
        FechamentoBimestreComponent,
        FechamentoBimestreUpdateComponent,
        FechamentoBimestreDeleteDialogComponent,
        FechamentoBimestreDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolFechamentoBimestreModule {}
