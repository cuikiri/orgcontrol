import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ConceitoAcompanhamentoComponent,
    ConceitoAcompanhamentoDetailComponent,
    ConceitoAcompanhamentoUpdateComponent,
    ConceitoAcompanhamentoDeletePopupComponent,
    ConceitoAcompanhamentoDeleteDialogComponent,
    conceitoAcompanhamentoRoute,
    conceitoAcompanhamentoPopupRoute
} from './';

const ENTITY_STATES = [...conceitoAcompanhamentoRoute, ...conceitoAcompanhamentoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConceitoAcompanhamentoComponent,
        ConceitoAcompanhamentoDetailComponent,
        ConceitoAcompanhamentoUpdateComponent,
        ConceitoAcompanhamentoDeleteDialogComponent,
        ConceitoAcompanhamentoDeletePopupComponent
    ],
    entryComponents: [
        ConceitoAcompanhamentoComponent,
        ConceitoAcompanhamentoUpdateComponent,
        ConceitoAcompanhamentoDeleteDialogComponent,
        ConceitoAcompanhamentoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolConceitoAcompanhamentoModule {}
