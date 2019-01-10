import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RespostaAtividadeComponent,
    RespostaAtividadeDetailComponent,
    RespostaAtividadeUpdateComponent,
    RespostaAtividadeDeletePopupComponent,
    RespostaAtividadeDeleteDialogComponent,
    respostaAtividadeRoute,
    respostaAtividadePopupRoute
} from './';

const ENTITY_STATES = [...respostaAtividadeRoute, ...respostaAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RespostaAtividadeComponent,
        RespostaAtividadeDetailComponent,
        RespostaAtividadeUpdateComponent,
        RespostaAtividadeDeleteDialogComponent,
        RespostaAtividadeDeletePopupComponent
    ],
    entryComponents: [
        RespostaAtividadeComponent,
        RespostaAtividadeUpdateComponent,
        RespostaAtividadeDeleteDialogComponent,
        RespostaAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRespostaAtividadeModule {}
