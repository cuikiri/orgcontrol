import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    BimestreComponent,
    BimestreDetailComponent,
    BimestreUpdateComponent,
    BimestreDeletePopupComponent,
    BimestreDeleteDialogComponent,
    bimestreRoute,
    bimestrePopupRoute
} from './';

const ENTITY_STATES = [...bimestreRoute, ...bimestrePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BimestreComponent,
        BimestreDetailComponent,
        BimestreUpdateComponent,
        BimestreDeleteDialogComponent,
        BimestreDeletePopupComponent
    ],
    entryComponents: [BimestreComponent, BimestreUpdateComponent, BimestreDeleteDialogComponent, BimestreDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolBimestreModule {}
