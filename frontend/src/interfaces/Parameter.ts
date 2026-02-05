import type ParameterEntry from "./ParameterEntry";

export default interface Parameter {
    id: number,
    name: string,
    unit: string,
    entries: ParameterEntry[]
}