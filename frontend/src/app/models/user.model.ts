import { Role } from "./role.model";

export interface User {
    username: string;
    name: string;
    email: string;
    password: string;
    role: Role[];
}