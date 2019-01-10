import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    DadoBiologicoComponent,
    DadoBiologicoDetailComponent,
    DadoBiologicoUpdateComponent,
    DadoBiologicoDeletePopupComponent,
    DadoBiologicoDeleteDialogComponent,
    dadoBiologicoRoute,
    dadoBiologicoPopupRoute
} from './';

const ENTITY_STATES = [...dadoBiologicoRoute, ...dadoBiologicoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DadoBiologicoComponent,
        DadoBiologicoDetailComponent,
        DadoBiologicoUpdateComponent,
        DadoBiologicoDeleteDialogComponent,
        DadoBiologicoDeletePopupComponent
    ],
    entryComponents: [
        DadoBiologicoComponent,
        DadoBiologicoUpdateComponent,
        DadoBiologicoDeleteDialogComponent,
        DadoBiologicoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolDadoBiologicoModule {}
