import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

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
}
