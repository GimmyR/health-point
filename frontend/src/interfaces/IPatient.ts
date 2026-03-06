import type Account from "./Account";
import type Parameter from "./Parameter";

export default interface IPatient {
    id: string,
    room: string,
    diagnosis: string,
    account: Account,
    parameters: Parameter[],
    entryDates: string[]
}