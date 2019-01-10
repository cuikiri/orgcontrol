import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespAvalOptativaEconomicaComponent,
    RespAvalOptativaEconomicaDetailComponent,
    RespAvalOptativaEconomicaUpdateComponent,
    RespAvalOptativaEconomicaDeletePopupComponent,
    RespAvalOptativaEconomicaDeleteDialogComponent,
    respAvalOptativaEconomicaRoute,
    respAvalOptativaEconomicaPopupRoute
} from './';

const ENTITY_STATES = [...respAvalOptativaEconomicaRoute, ...respAvalOptativaEconomicaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespAvalOptativaEconomicaComponent,
        RespAvalOptativaEconomicaDetailComponent,
        RespAvalOptativaEconomicaUpdateComponent,
        RespAvalOptativaEconomicaDeleteDialogComponent,
        RespAvalOptativaEconomicaDeletePopupComponent
    ],
    entryComponents: [
        RespAvalOptativaEconomicaComponent,
        RespAvalOptativaEconomicaUpdateComponent,
        RespAvalOptativaEconomicaDeleteDialogComponent,
        RespAvalOptativaEconomicaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespAvalOptativaEconomicaModule {}
