import { AsyncPipe } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { Observable } from "rxjs";

interface Todo {
  id: string;
  title: string;
}

@Component({
  selector: "app-todo-list",
  standalone: true,
  imports: [
    AsyncPipe,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
  ],
  templateUrl: "./todo-list.component.html",
  styleUrl: "./todo-list.component.css",
})
export class TodoListComponent {
  todos$!: Observable<Todo[]>;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.todos$ = this.http.get<Todo[]>("todos");
  }

  addTodo(event: SubmitEvent) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const title = new FormData(form).get("title") as string;
    this.http.post("todos", { title }).subscribe(() => {
      form.reset();
      this.todos$ = this.http.get<Todo[]>("todos");
    });
  }
}
