import { Injectable } from '@angular/core';

interface DisqusConfig {
  [key: string]: any;
}

interface WindowWithDisqusConfig extends Window {
  disqus_config?: DisqusConfig;
}

@Injectable({
  providedIn: 'root'
})
export class DisqusService {

  private readonly shortname = 'your-gaming-news';

  constructor() { }

  loadDisqus() {
    const windowWithConfig = window as WindowWithDisqusConfig;
    if (windowWithConfig) {
      windowWithConfig.disqus_config = this.getDisqusConfig();
      const script = windowWithConfig.document.createElement('script');
      script.src = `https://${this.shortname}.disqus.com/embed.js`;
      script.async = true;
      script.setAttribute('data-timestamp', `${+new Date()}`);
      windowWithConfig.document.body.appendChild(script);
    }
  }

  private getDisqusConfig(): DisqusConfig {
    return function () {
    };
  }
}
