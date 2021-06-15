import { MemberService } from './../member.service';
import { Member } from './../memberdash/member';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-member-details',
  templateUrl: './member-details.component.html',
  styleUrls: ['./member-details.component.css']
})
export class MemberDetailsComponent implements OnInit {

  id: number;
  m: Member;
  ph = '';

  constructor(private router: Router, private route: ActivatedRoute,
              private memberService: MemberService) { }

  ngOnInit(): void {
    this.loadData();
  }

  loadData() {
    this.m = new Member();

    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));

    // tslint:disable-next-line: deprecation
    this.memberService.getMemberById(this.id).subscribe(data => {
      console.log(data);
      this.m = data;
    }, error => console.log(error));
  }

  updatePhone(p: string) {
    console.log(p);
    this.memberService.updatePhone(this.m, p)
      // tslint:disable-next-line: deprecation
      .subscribe(data => {
        console.log(data);
      }, error => console.log(error));
  }

  updateWallet(bal: string) {
    console.log(bal);
    // tslint:disable-next-line: radix
    if (parseInt(bal) > this.m.walletbalance) {
      // tslint:disable-next-line: deprecation
      this.memberService.updateWallet(this.m, parseInt(bal, 10)).subscribe(data => {
        console.log(data);
      }, error => console.log(error));
    }
  }

  updatePassword(pass: string) {
    console.log(pass);
    // tslint:disable-next-line: deprecation
    this.memberService.updatePassword(this.m, pass).subscribe(data => {
      console.log(data);
    }, error => console.log(error));
  }

  changePassword() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
  }

  showCurrentBookings() {
    // tslint:disable-next-line: radix
    this.id = parseInt(this.route.snapshot.paramMap.get('id'));
    this.router.navigate(['current/', this.id]);
  }
}
