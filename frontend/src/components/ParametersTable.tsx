import { useNavigate } from "react-router-dom";
import type Parameter from "../interfaces/Parameter";

type Props = { 
    patientId?: number,
    isStaff?: boolean, 
    dateTimes: string[], 
    parameters: Parameter[] 
};

export default function ParametersTable({ patientId, isStaff, dateTimes, parameters } : Props) {
    const navigate = useNavigate();

    const editData = (parameter: Parameter, datetime: string) => {
        const entryId = findEntry(parameter, datetime)?.id;
        if(isStaff && entryId != undefined)
            navigate(`/entry/edit/${entryId}?patient=${patientId}`);
    };

    const editParameter = (parameterId: number) => {
        navigate(`/edit-parameter?id=${parameterId}&patient=${patientId}`);
    };

    const findEntry = (parameter: Parameter, datetime: string) => {
        return parameter.entries.find(entry => entry.entryDate == datetime);
    };

    return <>
        {parameters.length > 0 && <table className="table table-bordered text-center">
            <thead>
                <tr>
                    <th>Date & time</th>
                    {parameters.map(param => <th key={`th-${param.id}`} onClick={() => editParameter(param.id)}>{param.name}</th>)}
                </tr>
            </thead>
            <tbody>
                {dateTimes.map(dt => <tr key={dt}>
                    <td>{new Date(dt).toLocaleString()}</td>
                    {parameters.map(param => <td key={`${dt} ${param.id}`} onClick={() => editData(param, dt)}>{findEntry(param, dt)?.value}</td>)}
                </tr>)}
            </tbody>
        </table>}
        {parameters.entries.length == 0 && <div className="text-bg-secondary text-uppercase fw-bold text-center py-3">No data</div>}
    </>
}