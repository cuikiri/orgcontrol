import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoBiotipoComponent,
    TipoBiotipoDetailComponent,
    TipoBiotipoUpdateComponent,
    TipoBiotipoDeletePopupComponent,
    TipoBiotipoDeleteDialogComponent,
    tipoBiotipoRoute,
    tipoBiotipoPopupRoute
} from './';

const ENTITY_STATES = [...tipoBiotipoRoute, ...tipoBiotipoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoBiotipoComponent,
        TipoBiotipoDetailComponent,
        TipoBiotipoUpdateComponent,
        TipoBiotipoDeleteDialogComponent,
        TipoBiotipoDeletePopupComponent
    ],
    entryComponents: [TipoBiotipoComponent, TipoBiotipoUpdateComponent, TipoBiotipoDeleteDialogComponent, TipoBiotipoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoBiotipoModule {}
