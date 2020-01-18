import { Component, TemplateRef } from '@angular/core';
import {Router} from '@angular/router';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {
  title = 'kriauto-web';
  isconnexion;
  islogin;
  islogout;

  ngOnInit() {
    //console.log("init");
    this.isconnexion = true;
    this.islogin = false;
    this.islogout = true;
  }

  modalRef: BsModalRef;

  constructor(private router: Router,private modalService: BsModalService) {}

  connexion(){
    this.islogin = true;
    this.islogout = true;
    this.isconnexion = false;
  }

  login(){
    this.islogin = true;
    this.islogout = false;
    this.isconnexion = true;
  }

  logout(){
    this.islogin = false;
    this.islogout = true;
    this.isconnexion = true;
    this.router.navigate([''])
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }
}
