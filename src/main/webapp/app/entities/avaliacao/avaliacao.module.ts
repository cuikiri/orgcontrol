import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AvaliacaoComponent,
    AvaliacaoDetailComponent,
    AvaliacaoUpdateComponent,
    AvaliacaoDeletePopupComponent,
    AvaliacaoDeleteDialogComponent,
    avaliacaoRoute,
    avaliacaoPopupRoute
} from './';

const ENTITY_STATES = [...avaliacaoRoute, ...avaliacaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AvaliacaoComponent,
        AvaliacaoDetailComponent,
        AvaliacaoUpdateComponent,
        AvaliacaoDeleteDialogComponent,
        AvaliacaoDeletePopupComponent
    ],
    entryComponents: [AvaliacaoComponent, AvaliacaoUpdateComponent, AvaliacaoDeleteDialogComponent, AvaliacaoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAvaliacaoModule {}
