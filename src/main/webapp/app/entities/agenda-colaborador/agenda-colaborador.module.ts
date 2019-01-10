import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    AgendaColaboradorComponent,
    AgendaColaboradorDetailComponent,
    AgendaColaboradorUpdateComponent,
    AgendaColaboradorDeletePopupComponent,
    AgendaColaboradorDeleteDialogComponent,
    agendaColaboradorRoute,
    agendaColaboradorPopupRoute
} from './';

const ENTITY_STATES = [...agendaColaboradorRoute, ...agendaColaboradorPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AgendaColaboradorComponent,
        AgendaColaboradorDetailComponent,
        AgendaColaboradorUpdateComponent,
        AgendaColaboradorDeleteDialogComponent,
        AgendaColaboradorDeletePopupComponent
    ],
    entryComponents: [
        AgendaColaboradorComponent,
        AgendaColaboradorUpdateComponent,
        AgendaColaboradorDeleteDialogComponent,
        AgendaColaboradorDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolAgendaColaboradorModule {}
