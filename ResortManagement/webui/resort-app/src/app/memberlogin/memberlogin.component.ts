import { MemberService } from './../member.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Member } from '../memberdash/member';

@Component({
  selector: 'app-memberlogin',
  templateUrl: './memberlogin.component.html',
  styleUrls: ['./memberlogin.component.css']
})
export class MemberLoginComponent implements OnInit {

  email: string;
  passCode: string;
  member: Member = new Member();
  product = '';

  constructor(private router: Router, private memberService: MemberService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {

  }

  loadRegister(): void {
    this.router.navigate(['register']);
  }

  login(): void {
    this.product = this.route.snapshot.paramMap.get('val');
    this.memberService.getMemberByEmail(this.email)
      // tslint:disable-next-line: deprecation
      .subscribe(data => {
        console.log(data);
        this.member = data;

        if (this.passCode === this.member.passKey) {
          this.router.navigate(['memDash/', this.product, this.member.memberId]);
        }
      });
  }
}
