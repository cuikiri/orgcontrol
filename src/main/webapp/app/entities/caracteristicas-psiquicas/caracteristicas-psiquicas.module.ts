import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    CaracteristicasPsiquicasComponent,
    CaracteristicasPsiquicasDetailComponent,
    CaracteristicasPsiquicasUpdateComponent,
    CaracteristicasPsiquicasDeletePopupComponent,
    CaracteristicasPsiquicasDeleteDialogComponent,
    caracteristicasPsiquicasRoute,
    caracteristicasPsiquicasPopupRoute
} from './';

const ENTITY_STATES = [...caracteristicasPsiquicasRoute, ...caracteristicasPsiquicasPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CaracteristicasPsiquicasComponent,
        CaracteristicasPsiquicasDetailComponent,
        CaracteristicasPsiquicasUpdateComponent,
        CaracteristicasPsiquicasDeleteDialogComponent,
        CaracteristicasPsiquicasDeletePopupComponent
    ],
    entryComponents: [
        CaracteristicasPsiquicasComponent,
        CaracteristicasPsiquicasUpdateComponent,
        CaracteristicasPsiquicasDeleteDialogComponent,
        CaracteristicasPsiquicasDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolCaracteristicasPsiquicasModule {}
