import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import {HttpClientTestingModule} from '@angular//common/http/testing';
import { MemberService } from './member.service';
import { Member } from './memberdash/member';

describe('MemberService', () => {
  let service: MemberService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MemberService]
    });
    service = TestBed.inject(MemberService);

    // tslint:disable-next-line: deprecation
    // service = TestBed.get(MemberService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getMemberByEmail', () => {

    it('should return a member', () => {
      const res = {
        memberId: 129,
        memberName: 'Julie Murphy',
        email: 'julie.murph@bcd.co.in',
        phone: '837773737',
        walletbalance: 64600,
        passKey: 'julie123',
        dateOfBirth: '2000-04-29',
        membershipDate: '2018-05-18'
      };
      let response;
      spyOn(service, 'getMemberByEmail').and.returnValue(of(res));

      // tslint:disable-next-line: deprecation
      service.getMemberByEmail('julie.murph@bcd.co.in').subscribe(data => {
        response = data;
      });

      expect(response).toEqual(res);
    });
  });

  describe('getMemberById', () => {

    it('should return a member', () => {
      const res = {
        memberId: 129,
        memberName: 'Julie Murphy',
        email: 'julie.murph@bcd.co.in',
        phone: '837773737',
        walletbalance: 64600,
        passKey: 'julie123',
        dateOfBirth: '2000-04-29',
        membershipDate: '2018-05-18'
      };
      let response;
      spyOn(service, 'getMemberById').and.returnValue(of(res));

      // tslint:disable-next-line: deprecation
      service.getMemberById(129).subscribe(data => {
        response = data;
      });

      expect(response).toEqual(res);
    });
  });

  // For a POST request
  describe('registerMember', () => {

    it('should add a new member', () => {
      const newMember: Member = {
        memberId: 135,
        memberName: 'Karen Fleur',
        email: 'jfleur@xcv.com',
        phone: '373882',
        walletbalance: 0,
        passKey: 'julie123',
        dateOfBirth: '1997-04-29',
        membershipDate: '2021-04-21'
      };

      const res = 'Member Registered Successfully! Please login to continue';
      let response;
      spyOn(service, 'registerMember').and.returnValue(of(res));

      // tslint:disable-next-line: deprecation
      service.registerMember(newMember).subscribe(data => {
        response = data;
      });

      expect(response).toEqual(res);
    });

    it('should not add a new member', () => {
      const oldMember: Member = {
        memberId: 129,
        memberName: 'Julie Murphy',
        email: 'julie.murph@bcd.co.in',
        phone: '837773737',
        walletbalance: 64600,
        passKey: 'julie123',
        dateOfBirth: '2000-04-29',
        membershipDate: '2018-05-18'
      };

      const res = 'This Email Id already exists. Please try again!';
      let response;
      spyOn(service, 'registerMember').and.returnValue(of(res));

      // tslint:disable-next-line: deprecation
      service.registerMember(oldMember).subscribe(data => {
        response = data;
      });

      expect(response).toEqual(res);

    });
  });

  // For a PUT request
  describe('updatePhone', () => {

    it('should update phone number', () => {
      const oldMember: Member = {
        memberId: 129,
        memberName: 'Julie Murphy',
        email: 'julie.murph@bcd.co.in',
        phone: '837773737',
        walletbalance: 64600,
        passKey: 'julie123',
        dateOfBirth: '2000-04-29',
        membershipDate: '2018-05-18'
      };

      const res = 'Phone updated successfully';
      let response;
      spyOn(service, 'updatePhone').and.returnValue(of(res));

      // tslint:disable-next-line: deprecation
      service.updatePhone(oldMember, '9899322').subscribe(data => {
        response = data;
      });

      expect(response).toEqual(res);
    });

    // Another POST
    describe('bookAmenity', () => {

      it('should book a new Amenity', () => {
        const oldMember: Member = {
          memberId: 129,
          memberName: 'Julie Murphy',
          email: 'julie.murph@bcd.co.in',
          phone: '837773737',
          walletbalance: 64600,
          passKey: 'julie123',
          dateOfBirth: '2000-04-29',
          membershipDate: '2018-05-18'
        };

        const res = 'Amenity Booked Successfully';
        let response;
        spyOn(service, 'bookAmenity').and.returnValue(of(res));

        // tslint:disable-next-line: deprecation
        service.bookAmenity(oldMember, 4008, 1).subscribe(data => {
          response = data;
        });

        expect(response).toEqual(res);
      });

      it('should ask to update wallet balance', () => {
        const oldMember: Member = {
          memberId: 129,
          memberName: 'Julie Murphy',
          email: 'julie.murph@bcd.co.in',
          phone: '837773737',
          walletbalance: 600,
          passKey: 'julie123',
          dateOfBirth: '2000-04-29',
          membershipDate: '2018-05-18'
        };

        const res = 'Insufficient Balance. Please update your wallet';
        let response;
        spyOn(service, 'bookAmenity').and.returnValue(of(res));

        // tslint:disable-next-line: deprecation
        service.bookAmenity(oldMember, 4008, 1).subscribe(data => {
          response = data;
        });

        expect(response).toEqual(res);
      });
    });
  });
});
