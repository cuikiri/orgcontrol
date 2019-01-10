import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoContratacaoComponent,
    TipoContratacaoDetailComponent,
    TipoContratacaoUpdateComponent,
    TipoContratacaoDeletePopupComponent,
    TipoContratacaoDeleteDialogComponent,
    tipoContratacaoRoute,
    tipoContratacaoPopupRoute
} from './';

const ENTITY_STATES = [...tipoContratacaoRoute, ...tipoContratacaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoContratacaoComponent,
        TipoContratacaoDetailComponent,
        TipoContratacaoUpdateComponent,
        TipoContratacaoDeleteDialogComponent,
        TipoContratacaoDeletePopupComponent
    ],
    entryComponents: [
        TipoContratacaoComponent,
        TipoContratacaoUpdateComponent,
        TipoContratacaoDeleteDialogComponent,
        TipoContratacaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoContratacaoModule {}
