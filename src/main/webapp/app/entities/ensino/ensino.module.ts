import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    EnsinoComponent,
    EnsinoDetailComponent,
    EnsinoUpdateComponent,
    EnsinoDeletePopupComponent,
    EnsinoDeleteDialogComponent,
    ensinoRoute,
    ensinoPopupRoute
} from './';

const ENTITY_STATES = [...ensinoRoute, ...ensinoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [EnsinoComponent, EnsinoDetailComponent, EnsinoUpdateComponent, EnsinoDeleteDialogComponent, EnsinoDeletePopupComponent],
    entryComponents: [EnsinoComponent, EnsinoUpdateComponent, EnsinoDeleteDialogComponent, EnsinoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolEnsinoModule {}
