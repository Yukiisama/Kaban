import { Component, Input, OnInit } from '@angular/core';
import { Release } from 'src/app/models/release.model';
import { Us } from 'src/app/models/us.model';
import { Version } from 'src/app/models/version.model';
import {ReleaseService} from '../../../../services/release.service';

@Component({
    selector: 'app-release-item',
    templateUrl: './release-item.component.html',
    styleUrls: ['./release-item.component.css']
})
export class ReleaseItemComponent implements OnInit {

    @Input() releaseItem: Release;
    userStories: Us[] = [];
    constructor(private releaseService: ReleaseService) { }

    ngOnInit(): void {
        this.getUserStories();
    }

    getFullVersion(): string{
        const version = Version.fromJSON(this.releaseItem.getVersion());
        return version.getFull();
    }

    getUserStories(): void {
        const us = this.releaseItem.getUserStories();
        us.forEach(item => this.userStories.push(Us.fromJSON(item)));
    }

    onDelete(): void {
        this.releaseService.delete(this.releaseItem.getProjectId(), this.releaseItem);
    }

}
