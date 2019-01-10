import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ChapaComponent,
    ChapaDetailComponent,
    ChapaUpdateComponent,
    ChapaDeletePopupComponent,
    ChapaDeleteDialogComponent,
    chapaRoute,
    chapaPopupRoute
} from './';

const ENTITY_STATES = [...chapaRoute, ...chapaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ChapaComponent, ChapaDetailComponent, ChapaUpdateComponent, ChapaDeleteDialogComponent, ChapaDeletePopupComponent],
    entryComponents: [ChapaComponent, ChapaUpdateComponent, ChapaDeleteDialogComponent, ChapaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolChapaModule {}
