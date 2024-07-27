import { HttpClient } from "@angular/common/http";
import { Component, EventEmitter, Output } from "@angular/core";
import { MatButtonModule } from "@angular/material/button";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";

@Component({
  selector: "app-add-todo-form",
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: "./add-todo-form.component.html",
  styleUrl: "./add-todo-form.component.css",
})
export class AddTodoFormComponent {
  @Output() todoAdded = new EventEmitter();

  constructor(private http: HttpClient) {}

  addTodo(event: SubmitEvent) {
    event.preventDefault();
    const form = event.target as HTMLFormElement;
    const title = new FormData(form).get("title") as string;
    this.http.post("todos", { title }).subscribe(() => {
      form.reset();
      this.todoAdded.emit();
    });
  }
}
