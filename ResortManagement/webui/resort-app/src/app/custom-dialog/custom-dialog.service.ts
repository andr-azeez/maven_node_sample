import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { CustomDialogComponent } from './custom-dialog.component';

@Injectable()
export class CustomDialogService {

  dialogRef: MatDialogRef<CustomDialogComponent>;

  constructor(private dialog: MatDialog) { }

  public open(options) {
    this.dialogRef = this.dialog.open(CustomDialogComponent, {
         data: {
           title: options.title,
           message: options.message
         }
    });
  }

  public confirmed(): Observable<any> {

    return this.dialogRef.afterClosed().pipe(take(1), map(res => {
        return res;
      }
    ));
  }
}
