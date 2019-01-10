import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    PlanejamentoAtividadeComponent,
    PlanejamentoAtividadeDetailComponent,
    PlanejamentoAtividadeUpdateComponent,
    PlanejamentoAtividadeDeletePopupComponent,
    PlanejamentoAtividadeDeleteDialogComponent,
    planejamentoAtividadeRoute,
    planejamentoAtividadePopupRoute
} from './';

const ENTITY_STATES = [...planejamentoAtividadeRoute, ...planejamentoAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanejamentoAtividadeComponent,
        PlanejamentoAtividadeDetailComponent,
        PlanejamentoAtividadeUpdateComponent,
        PlanejamentoAtividadeDeleteDialogComponent,
        PlanejamentoAtividadeDeletePopupComponent
    ],
    entryComponents: [
        PlanejamentoAtividadeComponent,
        PlanejamentoAtividadeUpdateComponent,
        PlanejamentoAtividadeDeleteDialogComponent,
        PlanejamentoAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolPlanejamentoAtividadeModule {}
