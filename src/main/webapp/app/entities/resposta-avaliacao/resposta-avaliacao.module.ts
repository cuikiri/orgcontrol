import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespostaAvaliacaoComponent,
    RespostaAvaliacaoDetailComponent,
    RespostaAvaliacaoUpdateComponent,
    RespostaAvaliacaoDeletePopupComponent,
    RespostaAvaliacaoDeleteDialogComponent,
    respostaAvaliacaoRoute,
    respostaAvaliacaoPopupRoute
} from './';

const ENTITY_STATES = [...respostaAvaliacaoRoute, ...respostaAvaliacaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespostaAvaliacaoComponent,
        RespostaAvaliacaoDetailComponent,
        RespostaAvaliacaoUpdateComponent,
        RespostaAvaliacaoDeleteDialogComponent,
        RespostaAvaliacaoDeletePopupComponent
    ],
    entryComponents: [
        RespostaAvaliacaoComponent,
        RespostaAvaliacaoUpdateComponent,
        RespostaAvaliacaoDeleteDialogComponent,
        RespostaAvaliacaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespostaAvaliacaoModule {}
