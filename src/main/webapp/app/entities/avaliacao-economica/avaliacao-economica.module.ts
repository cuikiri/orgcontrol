import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AvaliacaoEconomicaComponent,
    AvaliacaoEconomicaDetailComponent,
    AvaliacaoEconomicaUpdateComponent,
    AvaliacaoEconomicaDeletePopupComponent,
    AvaliacaoEconomicaDeleteDialogComponent,
    avaliacaoEconomicaRoute,
    avaliacaoEconomicaPopupRoute
} from './';

const ENTITY_STATES = [...avaliacaoEconomicaRoute, ...avaliacaoEconomicaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AvaliacaoEconomicaComponent,
        AvaliacaoEconomicaDetailComponent,
        AvaliacaoEconomicaUpdateComponent,
        AvaliacaoEconomicaDeleteDialogComponent,
        AvaliacaoEconomicaDeletePopupComponent
    ],
    entryComponents: [
        AvaliacaoEconomicaComponent,
        AvaliacaoEconomicaUpdateComponent,
        AvaliacaoEconomicaDeleteDialogComponent,
        AvaliacaoEconomicaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAvaliacaoEconomicaModule {}
