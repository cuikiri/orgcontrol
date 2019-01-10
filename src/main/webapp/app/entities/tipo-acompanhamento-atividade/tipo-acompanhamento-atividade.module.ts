import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoAcompanhamentoAtividadeComponent,
    TipoAcompanhamentoAtividadeDetailComponent,
    TipoAcompanhamentoAtividadeUpdateComponent,
    TipoAcompanhamentoAtividadeDeletePopupComponent,
    TipoAcompanhamentoAtividadeDeleteDialogComponent,
    tipoAcompanhamentoAtividadeRoute,
    tipoAcompanhamentoAtividadePopupRoute
} from './';

const ENTITY_STATES = [...tipoAcompanhamentoAtividadeRoute, ...tipoAcompanhamentoAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoAcompanhamentoAtividadeComponent,
        TipoAcompanhamentoAtividadeDetailComponent,
        TipoAcompanhamentoAtividadeUpdateComponent,
        TipoAcompanhamentoAtividadeDeleteDialogComponent,
        TipoAcompanhamentoAtividadeDeletePopupComponent
    ],
    entryComponents: [
        TipoAcompanhamentoAtividadeComponent,
        TipoAcompanhamentoAtividadeUpdateComponent,
        TipoAcompanhamentoAtividadeDeleteDialogComponent,
        TipoAcompanhamentoAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoAcompanhamentoAtividadeModule {}
