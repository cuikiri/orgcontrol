import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ColaboradorComponent,
    ColaboradorDetailComponent,
    ColaboradorUpdateComponent,
    ColaboradorDeletePopupComponent,
    ColaboradorDeleteDialogComponent,
    colaboradorRoute,
    colaboradorPopupRoute
} from './';

const ENTITY_STATES = [...colaboradorRoute, ...colaboradorPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ColaboradorComponent,
        ColaboradorDetailComponent,
        ColaboradorUpdateComponent,
        ColaboradorDeleteDialogComponent,
        ColaboradorDeletePopupComponent
    ],
    entryComponents: [ColaboradorComponent, ColaboradorUpdateComponent, ColaboradorDeleteDialogComponent, ColaboradorDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolColaboradorModule {}
