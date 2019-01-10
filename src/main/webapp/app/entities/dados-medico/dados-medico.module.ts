import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    DadosMedicoComponent,
    DadosMedicoDetailComponent,
    DadosMedicoUpdateComponent,
    DadosMedicoDeletePopupComponent,
    DadosMedicoDeleteDialogComponent,
    dadosMedicoRoute,
    dadosMedicoPopupRoute
} from './';

const ENTITY_STATES = [...dadosMedicoRoute, ...dadosMedicoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DadosMedicoComponent,
        DadosMedicoDetailComponent,
        DadosMedicoUpdateComponent,
        DadosMedicoDeleteDialogComponent,
        DadosMedicoDeletePopupComponent
    ],
    entryComponents: [DadosMedicoComponent, DadosMedicoUpdateComponent, DadosMedicoDeleteDialogComponent, DadosMedicoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolDadosMedicoModule {}
