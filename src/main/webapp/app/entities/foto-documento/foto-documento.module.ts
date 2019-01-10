import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    FotoDocumentoComponent,
    FotoDocumentoDetailComponent,
    FotoDocumentoUpdateComponent,
    FotoDocumentoDeletePopupComponent,
    FotoDocumentoDeleteDialogComponent,
    fotoDocumentoRoute,
    fotoDocumentoPopupRoute
} from './';

const ENTITY_STATES = [...fotoDocumentoRoute, ...fotoDocumentoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FotoDocumentoComponent,
        FotoDocumentoDetailComponent,
        FotoDocumentoUpdateComponent,
        FotoDocumentoDeleteDialogComponent,
        FotoDocumentoDeletePopupComponent
    ],
    entryComponents: [
        FotoDocumentoComponent,
        FotoDocumentoUpdateComponent,
        FotoDocumentoDeleteDialogComponent,
        FotoDocumentoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolFotoDocumentoModule {}
