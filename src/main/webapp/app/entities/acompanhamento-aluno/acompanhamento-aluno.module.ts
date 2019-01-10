import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AcompanhamentoAlunoComponent,
    AcompanhamentoAlunoDetailComponent,
    AcompanhamentoAlunoUpdateComponent,
    AcompanhamentoAlunoDeletePopupComponent,
    AcompanhamentoAlunoDeleteDialogComponent,
    acompanhamentoAlunoRoute,
    acompanhamentoAlunoPopupRoute
} from './';

const ENTITY_STATES = [...acompanhamentoAlunoRoute, ...acompanhamentoAlunoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AcompanhamentoAlunoComponent,
        AcompanhamentoAlunoDetailComponent,
        AcompanhamentoAlunoUpdateComponent,
        AcompanhamentoAlunoDeleteDialogComponent,
        AcompanhamentoAlunoDeletePopupComponent
    ],
    entryComponents: [
        AcompanhamentoAlunoComponent,
        AcompanhamentoAlunoUpdateComponent,
        AcompanhamentoAlunoDeleteDialogComponent,
        AcompanhamentoAlunoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAcompanhamentoAlunoModule {}
