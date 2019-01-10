import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    PlanejamentoInstituicaoComponent,
    PlanejamentoInstituicaoDetailComponent,
    PlanejamentoInstituicaoUpdateComponent,
    PlanejamentoInstituicaoDeletePopupComponent,
    PlanejamentoInstituicaoDeleteDialogComponent,
    planejamentoInstituicaoRoute,
    planejamentoInstituicaoPopupRoute
} from './';

const ENTITY_STATES = [...planejamentoInstituicaoRoute, ...planejamentoInstituicaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanejamentoInstituicaoComponent,
        PlanejamentoInstituicaoDetailComponent,
        PlanejamentoInstituicaoUpdateComponent,
        PlanejamentoInstituicaoDeleteDialogComponent,
        PlanejamentoInstituicaoDeletePopupComponent
    ],
    entryComponents: [
        PlanejamentoInstituicaoComponent,
        PlanejamentoInstituicaoUpdateComponent,
        PlanejamentoInstituicaoDeleteDialogComponent,
        PlanejamentoInstituicaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolPlanejamentoInstituicaoModule {}
