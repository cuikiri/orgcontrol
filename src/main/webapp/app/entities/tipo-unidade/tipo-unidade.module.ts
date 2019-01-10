import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoUnidadeComponent,
    TipoUnidadeDetailComponent,
    TipoUnidadeUpdateComponent,
    TipoUnidadeDeletePopupComponent,
    TipoUnidadeDeleteDialogComponent,
    tipoUnidadeRoute,
    tipoUnidadePopupRoute
} from './';

const ENTITY_STATES = [...tipoUnidadeRoute, ...tipoUnidadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoUnidadeComponent,
        TipoUnidadeDetailComponent,
        TipoUnidadeUpdateComponent,
        TipoUnidadeDeleteDialogComponent,
        TipoUnidadeDeletePopupComponent
    ],
    entryComponents: [TipoUnidadeComponent, TipoUnidadeUpdateComponent, TipoUnidadeDeleteDialogComponent, TipoUnidadeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoUnidadeModule {}
