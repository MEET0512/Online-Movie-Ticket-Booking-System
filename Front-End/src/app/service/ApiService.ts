import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: "any"
})

export class ApiService {
    private baseUrl = 'http://localhost:8181/api';

    constructor(private http: HttpClient){}

    getAllMovies(): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}/movies`);
    }
}