import type Account from "./Account";
import type Parameter from "./Parameter";
import type PatientDetails from "./PatientDetails";

export default interface IPatient {
    account: Account,
    details: PatientDetails,
    parameters: Parameter[],
    entryDates: string[]
}