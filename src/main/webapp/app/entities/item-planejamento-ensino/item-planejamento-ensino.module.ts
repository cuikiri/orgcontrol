import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ItemPlanejamentoEnsinoComponent,
    ItemPlanejamentoEnsinoDetailComponent,
    ItemPlanejamentoEnsinoUpdateComponent,
    ItemPlanejamentoEnsinoDeletePopupComponent,
    ItemPlanejamentoEnsinoDeleteDialogComponent,
    itemPlanejamentoEnsinoRoute,
    itemPlanejamentoEnsinoPopupRoute
} from './';

const ENTITY_STATES = [...itemPlanejamentoEnsinoRoute, ...itemPlanejamentoEnsinoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ItemPlanejamentoEnsinoComponent,
        ItemPlanejamentoEnsinoDetailComponent,
        ItemPlanejamentoEnsinoUpdateComponent,
        ItemPlanejamentoEnsinoDeleteDialogComponent,
        ItemPlanejamentoEnsinoDeletePopupComponent
    ],
    entryComponents: [
        ItemPlanejamentoEnsinoComponent,
        ItemPlanejamentoEnsinoUpdateComponent,
        ItemPlanejamentoEnsinoDeleteDialogComponent,
        ItemPlanejamentoEnsinoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolItemPlanejamentoEnsinoModule {}
