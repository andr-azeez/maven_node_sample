import { Component, OnInit, Inject, HostListener, ChangeDetectionStrategy } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-custom-dialog',
  templateUrl: './custom-dialog.component.html',
  styleUrls: ['./custom-dialog.component.css']
})
export class CustomDialogComponent implements OnInit {

  title: string;
  message: string;

  constructor(@Inject(MAT_DIALOG_DATA) public data: { message: string, title: string },
              public dialogRef: MatDialogRef<CustomDialogComponent>) {
    this.title = data.title;
    this.message = data.message;
  }

  ngOnInit(): void {
  }

  onConfirm(): void {
    this.dialogRef.close(true);
  }

  onDismiss(): void {
    this.dialogRef.close(false);
  }

  @HostListener('keydown.esc')
  public onEsc() {
    this.close(false);
  }

  public close(value) {
    this.dialogRef.close(value);
  }
}
