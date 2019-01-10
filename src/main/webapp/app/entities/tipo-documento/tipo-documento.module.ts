import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoDocumentoComponent,
    TipoDocumentoDetailComponent,
    TipoDocumentoUpdateComponent,
    TipoDocumentoDeletePopupComponent,
    TipoDocumentoDeleteDialogComponent,
    tipoDocumentoRoute,
    tipoDocumentoPopupRoute
} from './';

const ENTITY_STATES = [...tipoDocumentoRoute, ...tipoDocumentoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoDocumentoComponent,
        TipoDocumentoDetailComponent,
        TipoDocumentoUpdateComponent,
        TipoDocumentoDeleteDialogComponent,
        TipoDocumentoDeletePopupComponent
    ],
    entryComponents: [
        TipoDocumentoComponent,
        TipoDocumentoUpdateComponent,
        TipoDocumentoDeleteDialogComponent,
        TipoDocumentoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoDocumentoModule {}
