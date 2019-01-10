import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoAcompanhamentoAlunoComponent,
    TipoAcompanhamentoAlunoDetailComponent,
    TipoAcompanhamentoAlunoUpdateComponent,
    TipoAcompanhamentoAlunoDeletePopupComponent,
    TipoAcompanhamentoAlunoDeleteDialogComponent,
    tipoAcompanhamentoAlunoRoute,
    tipoAcompanhamentoAlunoPopupRoute
} from './';

const ENTITY_STATES = [...tipoAcompanhamentoAlunoRoute, ...tipoAcompanhamentoAlunoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoAcompanhamentoAlunoComponent,
        TipoAcompanhamentoAlunoDetailComponent,
        TipoAcompanhamentoAlunoUpdateComponent,
        TipoAcompanhamentoAlunoDeleteDialogComponent,
        TipoAcompanhamentoAlunoDeletePopupComponent
    ],
    entryComponents: [
        TipoAcompanhamentoAlunoComponent,
        TipoAcompanhamentoAlunoUpdateComponent,
        TipoAcompanhamentoAlunoDeleteDialogComponent,
        TipoAcompanhamentoAlunoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoAcompanhamentoAlunoModule {}
