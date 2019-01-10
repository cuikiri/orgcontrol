import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    EleicaoComponent,
    EleicaoDetailComponent,
    EleicaoUpdateComponent,
    EleicaoDeletePopupComponent,
    EleicaoDeleteDialogComponent,
    eleicaoRoute,
    eleicaoPopupRoute
} from './';

const ENTITY_STATES = [...eleicaoRoute, ...eleicaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        EleicaoComponent,
        EleicaoDetailComponent,
        EleicaoUpdateComponent,
        EleicaoDeleteDialogComponent,
        EleicaoDeletePopupComponent
    ],
    entryComponents: [EleicaoComponent, EleicaoUpdateComponent, EleicaoDeleteDialogComponent, EleicaoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolEleicaoModule {}
