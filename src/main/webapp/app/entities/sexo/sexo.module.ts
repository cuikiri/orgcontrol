import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    SexoComponent,
    SexoDetailComponent,
    SexoUpdateComponent,
    SexoDeletePopupComponent,
    SexoDeleteDialogComponent,
    sexoRoute,
    sexoPopupRoute
} from './';

const ENTITY_STATES = [...sexoRoute, ...sexoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [SexoComponent, SexoDetailComponent, SexoUpdateComponent, SexoDeleteDialogComponent, SexoDeletePopupComponent],
    entryComponents: [SexoComponent, SexoUpdateComponent, SexoDeleteDialogComponent, SexoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolSexoModule {}
