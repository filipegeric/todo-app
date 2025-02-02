import { AsyncPipe } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatListModule } from "@angular/material/list";
import { MatSelectModule } from "@angular/material/select";
import { BehaviorSubject, combineLatest, map, Observable } from "rxjs";
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
    MatSelectModule,
  ],
  templateUrl: "./todo-list.component.html",
  styleUrl: "./todo-list.component.css",
})
export class TodoListComponent {
  filteredTodos$!: Observable<Todo[]>;
  categories$!: Observable<string[]>;

  selectedCategory$ = new BehaviorSubject<string | null>(null);

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchTodos();
  }

  fetchTodos() {
    const todos$ = this.http.get<Todo[]>("todos");
    this.categories$ = todos$.pipe(
      map(todos => [...new Set(todos.map(todo => todo.category!).filter(Boolean))]),
    );
    this.filteredTodos$ = combineLatest([todos$, this.selectedCategory$]).pipe(
      map(([todos, selectedCategory]) =>
        !selectedCategory ? todos : todos.filter(todo => todo.category === selectedCategory),
      ),
    );
  }

  toggleDoneFor(todo: Todo) {
    this.http
      .patch(`todos/${todo.id}`, { id: todo.id, isDone: !todo.isDone })
      .subscribe(() => this.fetchTodos());
  }
}
