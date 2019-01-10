import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    FotoAcompanhamentoAlunoComponent,
    FotoAcompanhamentoAlunoDetailComponent,
    FotoAcompanhamentoAlunoUpdateComponent,
    FotoAcompanhamentoAlunoDeletePopupComponent,
    FotoAcompanhamentoAlunoDeleteDialogComponent,
    fotoAcompanhamentoAlunoRoute,
    fotoAcompanhamentoAlunoPopupRoute
} from './';

const ENTITY_STATES = [...fotoAcompanhamentoAlunoRoute, ...fotoAcompanhamentoAlunoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FotoAcompanhamentoAlunoComponent,
        FotoAcompanhamentoAlunoDetailComponent,
        FotoAcompanhamentoAlunoUpdateComponent,
        FotoAcompanhamentoAlunoDeleteDialogComponent,
        FotoAcompanhamentoAlunoDeletePopupComponent
    ],
    entryComponents: [
        FotoAcompanhamentoAlunoComponent,
        FotoAcompanhamentoAlunoUpdateComponent,
        FotoAcompanhamentoAlunoDeleteDialogComponent,
        FotoAcompanhamentoAlunoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolFotoAcompanhamentoAlunoModule {}
