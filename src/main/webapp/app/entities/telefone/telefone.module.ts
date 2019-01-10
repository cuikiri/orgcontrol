import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TelefoneComponent,
    TelefoneDetailComponent,
    TelefoneUpdateComponent,
    TelefoneDeletePopupComponent,
    TelefoneDeleteDialogComponent,
    telefoneRoute,
    telefonePopupRoute
} from './';

const ENTITY_STATES = [...telefoneRoute, ...telefonePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TelefoneComponent,
        TelefoneDetailComponent,
        TelefoneUpdateComponent,
        TelefoneDeleteDialogComponent,
        TelefoneDeletePopupComponent
    ],
    entryComponents: [TelefoneComponent, TelefoneUpdateComponent, TelefoneDeleteDialogComponent, TelefoneDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTelefoneModule {}
