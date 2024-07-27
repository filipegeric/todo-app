import { AsyncPipe } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { Observable } from "rxjs";
import { AddTodoFormComponent } from "../add-todo-form/add-todo-form.component";

interface Todo {
  id: string;
  title: string;
  category?: string;
  description?: string;
  isDone: boolean;
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
    AddTodoFormComponent,
    AddTodoFormComponent,
  ],
  templateUrl: "./todo-list.component.html",
  styleUrl: "./todo-list.component.css",
})
export class TodoListComponent {
  todos$!: Observable<Todo[]>;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchTodos();
  }

  fetchTodos() {
    this.todos$ = this.http.get<Todo[]>("todos");
  }

  toggleDoneFor(todo: Todo) {
    this.http
      .patch(`todos/${todo.id}`, { id: todo.id, isDone: !todo.isDone })
      .subscribe(() => this.fetchTodos());
  }
}
