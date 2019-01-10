import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ProblemaFisicoComponent,
    ProblemaFisicoDetailComponent,
    ProblemaFisicoUpdateComponent,
    ProblemaFisicoDeletePopupComponent,
    ProblemaFisicoDeleteDialogComponent,
    problemaFisicoRoute,
    problemaFisicoPopupRoute
} from './';

const ENTITY_STATES = [...problemaFisicoRoute, ...problemaFisicoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProblemaFisicoComponent,
        ProblemaFisicoDetailComponent,
        ProblemaFisicoUpdateComponent,
        ProblemaFisicoDeleteDialogComponent,
        ProblemaFisicoDeletePopupComponent
    ],
    entryComponents: [
        ProblemaFisicoComponent,
        ProblemaFisicoUpdateComponent,
        ProblemaFisicoDeleteDialogComponent,
        ProblemaFisicoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolProblemaFisicoModule {}
