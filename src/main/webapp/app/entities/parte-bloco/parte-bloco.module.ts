import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ParteBlocoComponent,
    ParteBlocoDetailComponent,
    ParteBlocoUpdateComponent,
    ParteBlocoDeletePopupComponent,
    ParteBlocoDeleteDialogComponent,
    parteBlocoRoute,
    parteBlocoPopupRoute
} from './';

const ENTITY_STATES = [...parteBlocoRoute, ...parteBlocoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParteBlocoComponent,
        ParteBlocoDetailComponent,
        ParteBlocoUpdateComponent,
        ParteBlocoDeleteDialogComponent,
        ParteBlocoDeletePopupComponent
    ],
    entryComponents: [ParteBlocoComponent, ParteBlocoUpdateComponent, ParteBlocoDeleteDialogComponent, ParteBlocoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolParteBlocoModule {}
