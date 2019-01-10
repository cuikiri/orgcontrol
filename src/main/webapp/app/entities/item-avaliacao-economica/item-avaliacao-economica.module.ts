import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ItemAvaliacaoEconomicaComponent,
    ItemAvaliacaoEconomicaDetailComponent,
    ItemAvaliacaoEconomicaUpdateComponent,
    ItemAvaliacaoEconomicaDeletePopupComponent,
    ItemAvaliacaoEconomicaDeleteDialogComponent,
    itemAvaliacaoEconomicaRoute,
    itemAvaliacaoEconomicaPopupRoute
} from './';

const ENTITY_STATES = [...itemAvaliacaoEconomicaRoute, ...itemAvaliacaoEconomicaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ItemAvaliacaoEconomicaComponent,
        ItemAvaliacaoEconomicaDetailComponent,
        ItemAvaliacaoEconomicaUpdateComponent,
        ItemAvaliacaoEconomicaDeleteDialogComponent,
        ItemAvaliacaoEconomicaDeletePopupComponent
    ],
    entryComponents: [
        ItemAvaliacaoEconomicaComponent,
        ItemAvaliacaoEconomicaUpdateComponent,
        ItemAvaliacaoEconomicaDeleteDialogComponent,
        ItemAvaliacaoEconomicaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolItemAvaliacaoEconomicaModule {}
