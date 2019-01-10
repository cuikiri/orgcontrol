import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoBlocoComponent,
    TipoBlocoDetailComponent,
    TipoBlocoUpdateComponent,
    TipoBlocoDeletePopupComponent,
    TipoBlocoDeleteDialogComponent,
    tipoBlocoRoute,
    tipoBlocoPopupRoute
} from './';

const ENTITY_STATES = [...tipoBlocoRoute, ...tipoBlocoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoBlocoComponent,
        TipoBlocoDetailComponent,
        TipoBlocoUpdateComponent,
        TipoBlocoDeleteDialogComponent,
        TipoBlocoDeletePopupComponent
    ],
    entryComponents: [TipoBlocoComponent, TipoBlocoUpdateComponent, TipoBlocoDeleteDialogComponent, TipoBlocoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoBlocoModule {}
