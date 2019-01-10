import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    TipoAtividadeComponent,
    TipoAtividadeDetailComponent,
    TipoAtividadeUpdateComponent,
    TipoAtividadeDeletePopupComponent,
    TipoAtividadeDeleteDialogComponent,
    tipoAtividadeRoute,
    tipoAtividadePopupRoute
} from './';

const ENTITY_STATES = [...tipoAtividadeRoute, ...tipoAtividadePopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TipoAtividadeComponent,
        TipoAtividadeDetailComponent,
        TipoAtividadeUpdateComponent,
        TipoAtividadeDeleteDialogComponent,
        TipoAtividadeDeletePopupComponent
    ],
    entryComponents: [
        TipoAtividadeComponent,
        TipoAtividadeUpdateComponent,
        TipoAtividadeDeleteDialogComponent,
        TipoAtividadeDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolTipoAtividadeModule {}
