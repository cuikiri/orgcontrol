import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    BimestreAcompanhamentoComponent,
    BimestreAcompanhamentoDetailComponent,
    BimestreAcompanhamentoUpdateComponent,
    BimestreAcompanhamentoDeletePopupComponent,
    BimestreAcompanhamentoDeleteDialogComponent,
    bimestreAcompanhamentoRoute,
    bimestreAcompanhamentoPopupRoute
} from './';

const ENTITY_STATES = [...bimestreAcompanhamentoRoute, ...bimestreAcompanhamentoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BimestreAcompanhamentoComponent,
        BimestreAcompanhamentoDetailComponent,
        BimestreAcompanhamentoUpdateComponent,
        BimestreAcompanhamentoDeleteDialogComponent,
        BimestreAcompanhamentoDeletePopupComponent
    ],
    entryComponents: [
        BimestreAcompanhamentoComponent,
        BimestreAcompanhamentoUpdateComponent,
        BimestreAcompanhamentoDeleteDialogComponent,
        BimestreAcompanhamentoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolBimestreAcompanhamentoModule {}
