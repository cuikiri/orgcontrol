import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    GeneralidadeComponent,
    GeneralidadeDetailComponent,
    GeneralidadeUpdateComponent,
    GeneralidadeDeletePopupComponent,
    GeneralidadeDeleteDialogComponent,
    generalidadeRoute,
    generalidadePopupRoute
} from './';

const ENTITY_STATES = [...generalidadeRoute, ...generalidadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GeneralidadeComponent,
        GeneralidadeDetailComponent,
        GeneralidadeUpdateComponent,
        GeneralidadeDeleteDialogComponent,
        GeneralidadeDeletePopupComponent
    ],
    entryComponents: [
        GeneralidadeComponent,
        GeneralidadeUpdateComponent,
        GeneralidadeDeleteDialogComponent,
        GeneralidadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolGeneralidadeModule {}
