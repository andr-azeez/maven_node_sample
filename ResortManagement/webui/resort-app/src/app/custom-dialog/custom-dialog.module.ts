import { CustomDialogComponent } from './custom-dialog.component';
import { CustomDialogService } from './custom-dialog.service';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { NgModule} from '@angular/core';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
    imports: [
        CommonModule,
        MatDialogModule,
        MatButtonModule
    ],
    declarations: [
        CustomDialogComponent
    ],
    exports: [CustomDialogComponent],
    entryComponents: [CustomDialogComponent],
    providers: [CustomDialogService]
  })
  export class CustomDialogModule {
  }
