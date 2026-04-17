import type { Role } from "./Role";

export interface IAccount {
    id: number;
    firstname: string;
    lastname: string;
    gender: string;
    roles: Role[]
}