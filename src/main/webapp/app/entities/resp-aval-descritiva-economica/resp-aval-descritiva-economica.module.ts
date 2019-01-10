import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespAvalDescritivaEconomicaComponent,
    RespAvalDescritivaEconomicaDetailComponent,
    RespAvalDescritivaEconomicaUpdateComponent,
    RespAvalDescritivaEconomicaDeletePopupComponent,
    RespAvalDescritivaEconomicaDeleteDialogComponent,
    respAvalDescritivaEconomicaRoute,
    respAvalDescritivaEconomicaPopupRoute
} from './';

const ENTITY_STATES = [...respAvalDescritivaEconomicaRoute, ...respAvalDescritivaEconomicaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespAvalDescritivaEconomicaComponent,
        RespAvalDescritivaEconomicaDetailComponent,
        RespAvalDescritivaEconomicaUpdateComponent,
        RespAvalDescritivaEconomicaDeleteDialogComponent,
        RespAvalDescritivaEconomicaDeletePopupComponent
    ],
    entryComponents: [
        RespAvalDescritivaEconomicaComponent,
        RespAvalDescritivaEconomicaUpdateComponent,
        RespAvalDescritivaEconomicaDeleteDialogComponent,
        RespAvalDescritivaEconomicaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespAvalDescritivaEconomicaModule {}
