import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    RacaComponent,
    RacaDetailComponent,
    RacaUpdateComponent,
    RacaDeletePopupComponent,
    RacaDeleteDialogComponent,
    racaRoute,
    racaPopupRoute
} from './';

const ENTITY_STATES = [...racaRoute, ...racaPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [RacaComponent, RacaDetailComponent, RacaUpdateComponent, RacaDeleteDialogComponent, RacaDeletePopupComponent],
    entryComponents: [RacaComponent, RacaUpdateComponent, RacaDeleteDialogComponent, RacaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolRacaModule {}
