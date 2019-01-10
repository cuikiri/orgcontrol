import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    BiotipoComponent,
    BiotipoDetailComponent,
    BiotipoUpdateComponent,
    BiotipoDeletePopupComponent,
    BiotipoDeleteDialogComponent,
    biotipoRoute,
    biotipoPopupRoute
} from './';

const ENTITY_STATES = [...biotipoRoute, ...biotipoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        BiotipoComponent,
        BiotipoDetailComponent,
        BiotipoUpdateComponent,
        BiotipoDeleteDialogComponent,
        BiotipoDeletePopupComponent
    ],
    entryComponents: [BiotipoComponent, BiotipoUpdateComponent, BiotipoDeleteDialogComponent, BiotipoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolBiotipoModule {}
