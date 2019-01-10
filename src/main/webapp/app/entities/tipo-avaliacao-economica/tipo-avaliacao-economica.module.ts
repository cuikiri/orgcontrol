import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoAvaliacaoEconomicaComponent,
    TipoAvaliacaoEconomicaDetailComponent,
    TipoAvaliacaoEconomicaUpdateComponent,
    TipoAvaliacaoEconomicaDeletePopupComponent,
    TipoAvaliacaoEconomicaDeleteDialogComponent,
    tipoAvaliacaoEconomicaRoute,
    tipoAvaliacaoEconomicaPopupRoute
} from './';

const ENTITY_STATES = [...tipoAvaliacaoEconomicaRoute, ...tipoAvaliacaoEconomicaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoAvaliacaoEconomicaComponent,
        TipoAvaliacaoEconomicaDetailComponent,
        TipoAvaliacaoEconomicaUpdateComponent,
        TipoAvaliacaoEconomicaDeleteDialogComponent,
        TipoAvaliacaoEconomicaDeletePopupComponent
    ],
    entryComponents: [
        TipoAvaliacaoEconomicaComponent,
        TipoAvaliacaoEconomicaUpdateComponent,
        TipoAvaliacaoEconomicaDeleteDialogComponent,
        TipoAvaliacaoEconomicaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoAvaliacaoEconomicaModule {}
