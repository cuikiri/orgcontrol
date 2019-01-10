import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RegistroRecuperacaoComponent,
    RegistroRecuperacaoDetailComponent,
    RegistroRecuperacaoUpdateComponent,
    RegistroRecuperacaoDeletePopupComponent,
    RegistroRecuperacaoDeleteDialogComponent,
    registroRecuperacaoRoute,
    registroRecuperacaoPopupRoute
} from './';

const ENTITY_STATES = [...registroRecuperacaoRoute, ...registroRecuperacaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RegistroRecuperacaoComponent,
        RegistroRecuperacaoDetailComponent,
        RegistroRecuperacaoUpdateComponent,
        RegistroRecuperacaoDeleteDialogComponent,
        RegistroRecuperacaoDeletePopupComponent
    ],
    entryComponents: [
        RegistroRecuperacaoComponent,
        RegistroRecuperacaoUpdateComponent,
        RegistroRecuperacaoDeleteDialogComponent,
        RegistroRecuperacaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRegistroRecuperacaoModule {}
