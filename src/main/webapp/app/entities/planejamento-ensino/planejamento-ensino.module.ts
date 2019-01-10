import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    PlanejamentoEnsinoComponent,
    PlanejamentoEnsinoDetailComponent,
    PlanejamentoEnsinoUpdateComponent,
    PlanejamentoEnsinoDeletePopupComponent,
    PlanejamentoEnsinoDeleteDialogComponent,
    planejamentoEnsinoRoute,
    planejamentoEnsinoPopupRoute
} from './';

const ENTITY_STATES = [...planejamentoEnsinoRoute, ...planejamentoEnsinoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanejamentoEnsinoComponent,
        PlanejamentoEnsinoDetailComponent,
        PlanejamentoEnsinoUpdateComponent,
        PlanejamentoEnsinoDeleteDialogComponent,
        PlanejamentoEnsinoDeletePopupComponent
    ],
    entryComponents: [
        PlanejamentoEnsinoComponent,
        PlanejamentoEnsinoUpdateComponent,
        PlanejamentoEnsinoDeleteDialogComponent,
        PlanejamentoEnsinoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolPlanejamentoEnsinoModule {}
