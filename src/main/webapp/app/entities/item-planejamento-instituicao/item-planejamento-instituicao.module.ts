import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrgcontrolSharedModule } from 'app/shared';
import {
    ItemPlanejamentoInstituicaoComponent,
    ItemPlanejamentoInstituicaoDetailComponent,
    ItemPlanejamentoInstituicaoUpdateComponent,
    ItemPlanejamentoInstituicaoDeletePopupComponent,
    ItemPlanejamentoInstituicaoDeleteDialogComponent,
    itemPlanejamentoInstituicaoRoute,
    itemPlanejamentoInstituicaoPopupRoute
} from './';

const ENTITY_STATES = [...itemPlanejamentoInstituicaoRoute, ...itemPlanejamentoInstituicaoPopupRoute];

@NgModule({
    imports: [OrgcontrolSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ItemPlanejamentoInstituicaoComponent,
        ItemPlanejamentoInstituicaoDetailComponent,
        ItemPlanejamentoInstituicaoUpdateComponent,
        ItemPlanejamentoInstituicaoDeleteDialogComponent,
        ItemPlanejamentoInstituicaoDeletePopupComponent
    ],
    entryComponents: [
        ItemPlanejamentoInstituicaoComponent,
        ItemPlanejamentoInstituicaoUpdateComponent,
        ItemPlanejamentoInstituicaoDeleteDialogComponent,
        ItemPlanejamentoInstituicaoDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrgcontrolItemPlanejamentoInstituicaoModule {}
