import { useEffect, useState, type FormEvent } from "react";
import type ParameterEntry from "../interfaces/ParameterEntry";
import Input from "./Input";
import type IPatient from "../interfaces/IPatient";
import { BACKEND } from "../lib/url";
import { useNavigate } from "react-router-dom";

type Props = {
    patientId: number,
    entry?: ParameterEntry
};

export default function AddEntryForm({ patientId, entry } : Props) {
    const navigate = useNavigate();
    const [error, setError] = useState<string>();
    const [patient, setPatient] = useState<IPatient>();

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const newEntry = {
            id: entry?.id,
            parameterId: parseInt(formData.get("parameter") as string),
            entryDate: formData.get("entry-date") as string,
            value: parseFloat(formData.get("value") as string)
        };

        const response = await fetch(`${BACKEND}/api/entry/save`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}`
            },
            body: JSON.stringify(newEntry)
        });

        const data = await response.text();

        if(response.status != 201)
            setError(data);

        else navigate(`/patient/${patientId}`);
    };

    useEffect(() => {
        if(patientId)
            fetch(`${BACKEND}/api/patients/${patientId}`, { 
                headers: { 
                    "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` 
                } 
            }).then(response => response.json())
                .then(data => setPatient(data))
                .catch(error => setError(error));
    }, []);

    return (
        <form className="pb-5" onSubmit={handleSubmit}>
            <Input type="datetime-local" id="entry-date" label="Entry date" inputValue={entry ? entry.entryDate : ""}/>
            <div className="d-flex flex-column flex-lg-row justify-content-between align-items-lg-center mb-3">
                <div className="me-lg-4">
                    <label htmlFor="parameter" className="form-label">Parameter</label>
                </div>
                <div className="col-lg-9">
                    <select id="parameter" className="form-select text-secondary" name="parameter" defaultValue={entry?.parameterId}>
                        {patient?.parameters.map(parameter => <option key={parameter.id} value={parameter.id}>{parameter.name}</option>)}
                    </select>
                </div>
            </div>
            <Input type="number" id="value" label="Value" inputValue={entry ? entry.value : ""}/>
            {error && <div className="text-center text-danger py-3">{error}</div>}
            <div className="d-flex flex-column align-items-end pt-3">
                <button type="submit" className="btn btn-primary col-12 col-lg-4 rounded-0">Submit</button>
            </div>
        </form>
    );
}