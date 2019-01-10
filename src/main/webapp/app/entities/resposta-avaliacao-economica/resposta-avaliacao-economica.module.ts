import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespostaAvaliacaoEconomicaComponent,
    RespostaAvaliacaoEconomicaDetailComponent,
    RespostaAvaliacaoEconomicaUpdateComponent,
    RespostaAvaliacaoEconomicaDeletePopupComponent,
    RespostaAvaliacaoEconomicaDeleteDialogComponent,
    respostaAvaliacaoEconomicaRoute,
    respostaAvaliacaoEconomicaPopupRoute
} from './';

const ENTITY_STATES = [...respostaAvaliacaoEconomicaRoute, ...respostaAvaliacaoEconomicaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespostaAvaliacaoEconomicaComponent,
        RespostaAvaliacaoEconomicaDetailComponent,
        RespostaAvaliacaoEconomicaUpdateComponent,
        RespostaAvaliacaoEconomicaDeleteDialogComponent,
        RespostaAvaliacaoEconomicaDeletePopupComponent
    ],
    entryComponents: [
        RespostaAvaliacaoEconomicaComponent,
        RespostaAvaliacaoEconomicaUpdateComponent,
        RespostaAvaliacaoEconomicaDeleteDialogComponent,
        RespostaAvaliacaoEconomicaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespostaAvaliacaoEconomicaModule {}
