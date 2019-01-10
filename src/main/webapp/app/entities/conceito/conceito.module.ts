import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ConceitoComponent,
    ConceitoDetailComponent,
    ConceitoUpdateComponent,
    ConceitoDeletePopupComponent,
    ConceitoDeleteDialogComponent,
    conceitoRoute,
    conceitoPopupRoute
} from './';

const ENTITY_STATES = [...conceitoRoute, ...conceitoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConceitoComponent,
        ConceitoDetailComponent,
        ConceitoUpdateComponent,
        ConceitoDeleteDialogComponent,
        ConceitoDeletePopupComponent
    ],
    entryComponents: [ConceitoComponent, ConceitoUpdateComponent, ConceitoDeleteDialogComponent, ConceitoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolConceitoModule {}
