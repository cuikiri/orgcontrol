import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoAvaliacaoComponent,
    TipoAvaliacaoDetailComponent,
    TipoAvaliacaoUpdateComponent,
    TipoAvaliacaoDeletePopupComponent,
    TipoAvaliacaoDeleteDialogComponent,
    tipoAvaliacaoRoute,
    tipoAvaliacaoPopupRoute
} from './';

const ENTITY_STATES = [...tipoAvaliacaoRoute, ...tipoAvaliacaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoAvaliacaoComponent,
        TipoAvaliacaoDetailComponent,
        TipoAvaliacaoUpdateComponent,
        TipoAvaliacaoDeleteDialogComponent,
        TipoAvaliacaoDeletePopupComponent
    ],
    entryComponents: [
        TipoAvaliacaoComponent,
        TipoAvaliacaoUpdateComponent,
        TipoAvaliacaoDeleteDialogComponent,
        TipoAvaliacaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoAvaliacaoModule {}
