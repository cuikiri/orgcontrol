import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoConceitoComponent,
    TipoConceitoDetailComponent,
    TipoConceitoUpdateComponent,
    TipoConceitoDeletePopupComponent,
    TipoConceitoDeleteDialogComponent,
    tipoConceitoRoute,
    tipoConceitoPopupRoute
} from './';

const ENTITY_STATES = [...tipoConceitoRoute, ...tipoConceitoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoConceitoComponent,
        TipoConceitoDetailComponent,
        TipoConceitoUpdateComponent,
        TipoConceitoDeleteDialogComponent,
        TipoConceitoDeletePopupComponent
    ],
    entryComponents: [
        TipoConceitoComponent,
        TipoConceitoUpdateComponent,
        TipoConceitoDeleteDialogComponent,
        TipoConceitoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoConceitoModule {}
