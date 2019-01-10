import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoParteBlocoComponent,
    TipoParteBlocoDetailComponent,
    TipoParteBlocoUpdateComponent,
    TipoParteBlocoDeletePopupComponent,
    TipoParteBlocoDeleteDialogComponent,
    tipoParteBlocoRoute,
    tipoParteBlocoPopupRoute
} from './';

const ENTITY_STATES = [...tipoParteBlocoRoute, ...tipoParteBlocoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoParteBlocoComponent,
        TipoParteBlocoDetailComponent,
        TipoParteBlocoUpdateComponent,
        TipoParteBlocoDeleteDialogComponent,
        TipoParteBlocoDeletePopupComponent
    ],
    entryComponents: [
        TipoParteBlocoComponent,
        TipoParteBlocoUpdateComponent,
        TipoParteBlocoDeleteDialogComponent,
        TipoParteBlocoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoParteBlocoModule {}
