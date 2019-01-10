import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ConteudoProgramaticoComponent,
    ConteudoProgramaticoDetailComponent,
    ConteudoProgramaticoUpdateComponent,
    ConteudoProgramaticoDeletePopupComponent,
    ConteudoProgramaticoDeleteDialogComponent,
    conteudoProgramaticoRoute,
    conteudoProgramaticoPopupRoute
} from './';

const ENTITY_STATES = [...conteudoProgramaticoRoute, ...conteudoProgramaticoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConteudoProgramaticoComponent,
        ConteudoProgramaticoDetailComponent,
        ConteudoProgramaticoUpdateComponent,
        ConteudoProgramaticoDeleteDialogComponent,
        ConteudoProgramaticoDeletePopupComponent
    ],
    entryComponents: [
        ConteudoProgramaticoComponent,
        ConteudoProgramaticoUpdateComponent,
        ConteudoProgramaticoDeleteDialogComponent,
        ConteudoProgramaticoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolConteudoProgramaticoModule {}
