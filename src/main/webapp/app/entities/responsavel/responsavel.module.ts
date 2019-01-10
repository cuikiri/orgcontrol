import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ResponsavelComponent,
    ResponsavelDetailComponent,
    ResponsavelUpdateComponent,
    ResponsavelDeletePopupComponent,
    ResponsavelDeleteDialogComponent,
    responsavelRoute,
    responsavelPopupRoute
} from './';

const ENTITY_STATES = [...responsavelRoute, ...responsavelPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ResponsavelComponent,
        ResponsavelDetailComponent,
        ResponsavelUpdateComponent,
        ResponsavelDeleteDialogComponent,
        ResponsavelDeletePopupComponent
    ],
    entryComponents: [ResponsavelComponent, ResponsavelUpdateComponent, ResponsavelDeleteDialogComponent, ResponsavelDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolResponsavelModule {}
