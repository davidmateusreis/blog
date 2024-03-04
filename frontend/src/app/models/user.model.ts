import { Role } from "./role.model";

export interface User {
    id: number;
    username: string;
    name: string;
    email: string;
    password: string;
    role: Role[];
    active: boolean;
}