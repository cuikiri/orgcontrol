import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoadoBiologicoComponent,
    TipoadoBiologicoDetailComponent,
    TipoadoBiologicoUpdateComponent,
    TipoadoBiologicoDeletePopupComponent,
    TipoadoBiologicoDeleteDialogComponent,
    tipoadoBiologicoRoute,
    tipoadoBiologicoPopupRoute
} from './';

const ENTITY_STATES = [...tipoadoBiologicoRoute, ...tipoadoBiologicoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoadoBiologicoComponent,
        TipoadoBiologicoDetailComponent,
        TipoadoBiologicoUpdateComponent,
        TipoadoBiologicoDeleteDialogComponent,
        TipoadoBiologicoDeletePopupComponent
    ],
    entryComponents: [
        TipoadoBiologicoComponent,
        TipoadoBiologicoUpdateComponent,
        TipoadoBiologicoDeleteDialogComponent,
        TipoadoBiologicoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoadoBiologicoModule {}
