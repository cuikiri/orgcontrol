import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AcompanhamentoEscolarAlunoComponent,
    AcompanhamentoEscolarAlunoDetailComponent,
    AcompanhamentoEscolarAlunoUpdateComponent,
    AcompanhamentoEscolarAlunoDeletePopupComponent,
    AcompanhamentoEscolarAlunoDeleteDialogComponent,
    acompanhamentoEscolarAlunoRoute,
    acompanhamentoEscolarAlunoPopupRoute
} from './';

const ENTITY_STATES = [...acompanhamentoEscolarAlunoRoute, ...acompanhamentoEscolarAlunoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AcompanhamentoEscolarAlunoComponent,
        AcompanhamentoEscolarAlunoDetailComponent,
        AcompanhamentoEscolarAlunoUpdateComponent,
        AcompanhamentoEscolarAlunoDeleteDialogComponent,
        AcompanhamentoEscolarAlunoDeletePopupComponent
    ],
    entryComponents: [
        AcompanhamentoEscolarAlunoComponent,
        AcompanhamentoEscolarAlunoUpdateComponent,
        AcompanhamentoEscolarAlunoDeleteDialogComponent,
        AcompanhamentoEscolarAlunoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAcompanhamentoEscolarAlunoModule {}
