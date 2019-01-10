import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    BlocoComponent,
    BlocoDetailComponent,
    BlocoUpdateComponent,
    BlocoDeletePopupComponent,
    BlocoDeleteDialogComponent,
    blocoRoute,
    blocoPopupRoute
} from './';

const ENTITY_STATES = [...blocoRoute, ...blocoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [BlocoComponent, BlocoDetailComponent, BlocoUpdateComponent, BlocoDeleteDialogComponent, BlocoDeletePopupComponent],
    entryComponents: [BlocoComponent, BlocoUpdateComponent, BlocoDeleteDialogComponent, BlocoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolBlocoModule {}
