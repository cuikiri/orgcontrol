import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    DiarioComponent,
    DiarioDetailComponent,
    DiarioUpdateComponent,
    DiarioDeletePopupComponent,
    DiarioDeleteDialogComponent,
    diarioRoute,
    diarioPopupRoute
} from './';

const ENTITY_STATES = [...diarioRoute, ...diarioPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DiarioComponent, DiarioDetailComponent, DiarioUpdateComponent, DiarioDeleteDialogComponent, DiarioDeletePopupComponent],
    entryComponents: [DiarioComponent, DiarioUpdateComponent, DiarioDeleteDialogComponent, DiarioDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolDiarioModule {}
