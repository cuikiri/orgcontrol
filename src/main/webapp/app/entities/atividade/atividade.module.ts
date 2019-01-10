import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AtividadeComponent,
    AtividadeDetailComponent,
    AtividadeUpdateComponent,
    AtividadeDeletePopupComponent,
    AtividadeDeleteDialogComponent,
    atividadeRoute,
    atividadePopupRoute
} from './';

const ENTITY_STATES = [...atividadeRoute, ...atividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AtividadeComponent,
        AtividadeDetailComponent,
        AtividadeUpdateComponent,
        AtividadeDeleteDialogComponent,
        AtividadeDeletePopupComponent
    ],
    entryComponents: [AtividadeComponent, AtividadeUpdateComponent, AtividadeDeleteDialogComponent, AtividadeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAtividadeModule {}
