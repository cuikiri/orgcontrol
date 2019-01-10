import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ObservacaoCoordenadorComponent,
    ObservacaoCoordenadorDetailComponent,
    ObservacaoCoordenadorUpdateComponent,
    ObservacaoCoordenadorDeletePopupComponent,
    ObservacaoCoordenadorDeleteDialogComponent,
    observacaoCoordenadorRoute,
    observacaoCoordenadorPopupRoute
} from './';

const ENTITY_STATES = [...observacaoCoordenadorRoute, ...observacaoCoordenadorPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ObservacaoCoordenadorComponent,
        ObservacaoCoordenadorDetailComponent,
        ObservacaoCoordenadorUpdateComponent,
        ObservacaoCoordenadorDeleteDialogComponent,
        ObservacaoCoordenadorDeletePopupComponent
    ],
    entryComponents: [
        ObservacaoCoordenadorComponent,
        ObservacaoCoordenadorUpdateComponent,
        ObservacaoCoordenadorDeleteDialogComponent,
        ObservacaoCoordenadorDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolObservacaoCoordenadorModule {}
