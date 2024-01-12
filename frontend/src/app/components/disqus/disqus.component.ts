import { Component, OnInit } from '@angular/core';
import { DisqusService } from 'src/app/services/disqus.service';

@Component({
  selector: 'app-disqus',
  templateUrl: './disqus.component.html',
  styleUrls: ['./disqus.component.css']
})
export class DisqusComponent implements OnInit {

  constructor(private disqusService: DisqusService) { }

  ngOnInit(): void {
    this.disqusService.loadDisqus();
  }

}
