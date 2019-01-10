import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    DiaSemanaComponent,
    DiaSemanaDetailComponent,
    DiaSemanaUpdateComponent,
    DiaSemanaDeletePopupComponent,
    DiaSemanaDeleteDialogComponent,
    diaSemanaRoute,
    diaSemanaPopupRoute
} from './';

const ENTITY_STATES = [...diaSemanaRoute, ...diaSemanaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DiaSemanaComponent,
        DiaSemanaDetailComponent,
        DiaSemanaUpdateComponent,
        DiaSemanaDeleteDialogComponent,
        DiaSemanaDeletePopupComponent
    ],
    entryComponents: [DiaSemanaComponent, DiaSemanaUpdateComponent, DiaSemanaDeleteDialogComponent, DiaSemanaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolDiaSemanaModule {}
