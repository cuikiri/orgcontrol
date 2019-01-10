import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    UfComponent,
    UfDetailComponent,
    UfUpdateComponent,
    UfDeletePopupComponent,
    UfDeleteDialogComponent,
    ufRoute,
    ufPopupRoute
} from './';

const ENTITY_STATES = [...ufRoute, ...ufPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [UfComponent, UfDetailComponent, UfUpdateComponent, UfDeleteDialogComponent, UfDeletePopupComponent],
    entryComponents: [UfComponent, UfUpdateComponent, UfDeleteDialogComponent, UfDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolUfModule {}
