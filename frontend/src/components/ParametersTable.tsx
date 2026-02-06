import type Parameter from "../interfaces/Parameter";

export default function ParametersTable({ dateTimes, parameters } : { dateTimes: string[], parameters: Parameter[] }) {
    return <table className="table table-bordered text-center">
        <thead>
            <tr>
                <th>Date & time</th>
                {parameters.map(param => <th key={`th-${param.id}`}>{param.name}</th>)}
            </tr>
        </thead>
        <tbody>
            {dateTimes.map(dt => <tr key={dt}>
                <td>{dt}</td>
                {parameters.map(param => <td key={`${dt} ${param.id}`}>{param.entries.find(entry => entry.dateTime == dt)?.value}</td>)}
            </tr>)}
        </tbody>
    </table>
}