import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToMemberLogin(event): void {
    alert('Please login to continue');
    const t = event.target;
    const val = t.attributes.id.nodeValue;
    // alert(val);
    this.router.navigate(['login/', val]);
  }

  goToEmployeeLogin(): void {
    this.router.navigate(['signin']);
  }

  showFeedback() {
    this.router.navigate(['contact/', 0]);
  }
}
