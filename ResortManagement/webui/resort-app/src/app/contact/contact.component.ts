import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  em: string;
  ph: string;
  fullname: string;
  msg: string;
  id: number;

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  loadMain() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    if (this.id !== 0) {
      this.router.navigate(['home/', this.id]);
    } else {
      this.router.navigate(['main']);
    }
  }

  sendQuery() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    if (this.em && this.ph && this.fullname && this.msg) {
      alert('Your Message has been Sent! Redirecting you back to Our Home Page!');
      if (this.id !== 0) {
        this.router.navigate(['home/', this.id]);
      } else {
        this.router.navigate(['main']);
      }
    } else {
      alert('Please fill all the fields!');
      this.router.navigate(['contact/', this.id]);
    }
  }
}
