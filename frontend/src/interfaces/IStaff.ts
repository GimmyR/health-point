import type Account from "./Account";

export interface IStaff {
    id: number;
    account: Account,
    admin: boolean
}