import { News } from "./news.model";

export interface NewsPage {
    news: News[];
    totalElements: number;
    totalPages: number;
}