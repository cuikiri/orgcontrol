import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoColaboradorComponent,
    TipoColaboradorDetailComponent,
    TipoColaboradorUpdateComponent,
    TipoColaboradorDeletePopupComponent,
    TipoColaboradorDeleteDialogComponent,
    tipoColaboradorRoute,
    tipoColaboradorPopupRoute
} from './';

const ENTITY_STATES = [...tipoColaboradorRoute, ...tipoColaboradorPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoColaboradorComponent,
        TipoColaboradorDetailComponent,
        TipoColaboradorUpdateComponent,
        TipoColaboradorDeleteDialogComponent,
        TipoColaboradorDeletePopupComponent
    ],
    entryComponents: [
        TipoColaboradorComponent,
        TipoColaboradorUpdateComponent,
        TipoColaboradorDeleteDialogComponent,
        TipoColaboradorDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoColaboradorModule {}
