import { useNavigate } from "react-router-dom";
import type Parameter from "../interfaces/Parameter";

export default function ParametersTable({ isStaff, dateTimes, parameters } : { isStaff?: boolean, dateTimes: string[], parameters: Parameter[] }) {
    const navigate = useNavigate();

    const editData = (dateTime: string, parameterId: number) => {
        if(isStaff)
            navigate(`/edit-entry/${parameterId}?dt=${dateTime}`);
    };

    return <table className="table table-bordered text-center">
        <thead>
            <tr>
                <th>Date & time</th>
                {parameters.map(param => <th key={`th-${param.id}`}>{param.name}</th>)}
            </tr>
        </thead>
        <tbody>
            {dateTimes.map(dt => <tr key={dt}>
                <td>{new Date(dt).toLocaleString()}</td>
                {parameters.map(param => <td key={`${dt} ${param.id}`} onClick={() => editData(dt, param.id)}>{param.entries.find(entry => entry.entryDate == dt)?.value}</td>)}
            </tr>)}
        </tbody>
    </table>
}