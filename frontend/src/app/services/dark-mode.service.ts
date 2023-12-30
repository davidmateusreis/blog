import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class DarkModeService {
  private darkMode = false;

  toggleDarkMode(): void {
    this.darkMode = !this.darkMode;
    this.updateDarkMode();
  }

  private updateDarkMode(): void {
    const body = document.body;
    if (this.darkMode) {
      body.classList.add('dark-mode');
    } else {
      body.classList.remove('dark-mode');
    }
  }

  isDarkMode(): boolean {
    return this.darkMode;
  }
}
