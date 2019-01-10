import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    OpcaoRespAvalOptativaEconomicaComponent,
    OpcaoRespAvalOptativaEconomicaDetailComponent,
    OpcaoRespAvalOptativaEconomicaUpdateComponent,
    OpcaoRespAvalOptativaEconomicaDeletePopupComponent,
    OpcaoRespAvalOptativaEconomicaDeleteDialogComponent,
    opcaoRespAvalOptativaEconomicaRoute,
    opcaoRespAvalOptativaEconomicaPopupRoute
} from './';

const ENTITY_STATES = [...opcaoRespAvalOptativaEconomicaRoute, ...opcaoRespAvalOptativaEconomicaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OpcaoRespAvalOptativaEconomicaComponent,
        OpcaoRespAvalOptativaEconomicaDetailComponent,
        OpcaoRespAvalOptativaEconomicaUpdateComponent,
        OpcaoRespAvalOptativaEconomicaDeleteDialogComponent,
        OpcaoRespAvalOptativaEconomicaDeletePopupComponent
    ],
    entryComponents: [
        OpcaoRespAvalOptativaEconomicaComponent,
        OpcaoRespAvalOptativaEconomicaUpdateComponent,
        OpcaoRespAvalOptativaEconomicaDeleteDialogComponent,
        OpcaoRespAvalOptativaEconomicaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolOpcaoRespAvalOptativaEconomicaModule {}
