import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AcompanhamentoAtividadeComponent,
    AcompanhamentoAtividadeDetailComponent,
    AcompanhamentoAtividadeUpdateComponent,
    AcompanhamentoAtividadeDeletePopupComponent,
    AcompanhamentoAtividadeDeleteDialogComponent,
    acompanhamentoAtividadeRoute,
    acompanhamentoAtividadePopupRoute
} from './';

const ENTITY_STATES = [...acompanhamentoAtividadeRoute, ...acompanhamentoAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AcompanhamentoAtividadeComponent,
        AcompanhamentoAtividadeDetailComponent,
        AcompanhamentoAtividadeUpdateComponent,
        AcompanhamentoAtividadeDeleteDialogComponent,
        AcompanhamentoAtividadeDeletePopupComponent
    ],
    entryComponents: [
        AcompanhamentoAtividadeComponent,
        AcompanhamentoAtividadeUpdateComponent,
        AcompanhamentoAtividadeDeleteDialogComponent,
        AcompanhamentoAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAcompanhamentoAtividadeModule {}
