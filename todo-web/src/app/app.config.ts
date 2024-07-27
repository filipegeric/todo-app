import { ApplicationConfig, provideZoneChangeDetection } from "@angular/core";
import { provideRouter } from "@angular/router";

import {
  HttpEvent,
  HttpHandlerFn,
  HttpRequest,
  provideHttpClient,
  withFetch,
  withInterceptors,
} from "@angular/common/http";
import { provideAnimationsAsync } from "@angular/platform-browser/animations/async";
import { Observable } from "rxjs";
import { routes } from "./app.routes";

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAnimationsAsync(),
    provideHttpClient(withFetch(), withInterceptors([baseUrlInterceptor])),
  ],
};

function baseUrlInterceptor(
  req: HttpRequest<unknown>,
  next: HttpHandlerFn,
): Observable<HttpEvent<unknown>> {
  return next(req.clone({ url: `api/${req.url}` }));
}
