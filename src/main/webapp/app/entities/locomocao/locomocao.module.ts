import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    LocomocaoComponent,
    LocomocaoDetailComponent,
    LocomocaoUpdateComponent,
    LocomocaoDeletePopupComponent,
    LocomocaoDeleteDialogComponent,
    locomocaoRoute,
    locomocaoPopupRoute
} from './';

const ENTITY_STATES = [...locomocaoRoute, ...locomocaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        LocomocaoComponent,
        LocomocaoDetailComponent,
        LocomocaoUpdateComponent,
        LocomocaoDeleteDialogComponent,
        LocomocaoDeletePopupComponent
    ],
    entryComponents: [LocomocaoComponent, LocomocaoUpdateComponent, LocomocaoDeleteDialogComponent, LocomocaoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolLocomocaoModule {}
