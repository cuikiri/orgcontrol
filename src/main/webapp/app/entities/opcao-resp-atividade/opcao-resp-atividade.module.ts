import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    OpcaoRespAtividadeComponent,
    OpcaoRespAtividadeDetailComponent,
    OpcaoRespAtividadeUpdateComponent,
    OpcaoRespAtividadeDeletePopupComponent,
    OpcaoRespAtividadeDeleteDialogComponent,
    opcaoRespAtividadeRoute,
    opcaoRespAtividadePopupRoute
} from './';

const ENTITY_STATES = [...opcaoRespAtividadeRoute, ...opcaoRespAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OpcaoRespAtividadeComponent,
        OpcaoRespAtividadeDetailComponent,
        OpcaoRespAtividadeUpdateComponent,
        OpcaoRespAtividadeDeleteDialogComponent,
        OpcaoRespAtividadeDeletePopupComponent
    ],
    entryComponents: [
        OpcaoRespAtividadeComponent,
        OpcaoRespAtividadeUpdateComponent,
        OpcaoRespAtividadeDeleteDialogComponent,
        OpcaoRespAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolOpcaoRespAtividadeModule {}
