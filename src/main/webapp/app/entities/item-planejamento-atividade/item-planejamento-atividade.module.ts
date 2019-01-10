import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ItemPlanejamentoAtividadeComponent,
    ItemPlanejamentoAtividadeDetailComponent,
    ItemPlanejamentoAtividadeUpdateComponent,
    ItemPlanejamentoAtividadeDeletePopupComponent,
    ItemPlanejamentoAtividadeDeleteDialogComponent,
    itemPlanejamentoAtividadeRoute,
    itemPlanejamentoAtividadePopupRoute
} from './';

const ENTITY_STATES = [...itemPlanejamentoAtividadeRoute, ...itemPlanejamentoAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ItemPlanejamentoAtividadeComponent,
        ItemPlanejamentoAtividadeDetailComponent,
        ItemPlanejamentoAtividadeUpdateComponent,
        ItemPlanejamentoAtividadeDeleteDialogComponent,
        ItemPlanejamentoAtividadeDeletePopupComponent
    ],
    entryComponents: [
        ItemPlanejamentoAtividadeComponent,
        ItemPlanejamentoAtividadeUpdateComponent,
        ItemPlanejamentoAtividadeDeleteDialogComponent,
        ItemPlanejamentoAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolItemPlanejamentoAtividadeModule {}
