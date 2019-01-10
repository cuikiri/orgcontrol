import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ItemAcompanhamentoAtividadeComponent,
    ItemAcompanhamentoAtividadeDetailComponent,
    ItemAcompanhamentoAtividadeUpdateComponent,
    ItemAcompanhamentoAtividadeDeletePopupComponent,
    ItemAcompanhamentoAtividadeDeleteDialogComponent,
    itemAcompanhamentoAtividadeRoute,
    itemAcompanhamentoAtividadePopupRoute
} from './';

const ENTITY_STATES = [...itemAcompanhamentoAtividadeRoute, ...itemAcompanhamentoAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ItemAcompanhamentoAtividadeComponent,
        ItemAcompanhamentoAtividadeDetailComponent,
        ItemAcompanhamentoAtividadeUpdateComponent,
        ItemAcompanhamentoAtividadeDeleteDialogComponent,
        ItemAcompanhamentoAtividadeDeletePopupComponent
    ],
    entryComponents: [
        ItemAcompanhamentoAtividadeComponent,
        ItemAcompanhamentoAtividadeUpdateComponent,
        ItemAcompanhamentoAtividadeDeleteDialogComponent,
        ItemAcompanhamentoAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolItemAcompanhamentoAtividadeModule {}
