import { useState, type FormEvent } from "react";
import type Parameter from "../interfaces/Parameter";
import Input from "./Input";
import { BACKEND } from "../lib/url";
import { useNavigate } from "react-router-dom";

type Props = {
    patientId: number,
    parameter?: Parameter
};

export default function AddParameterForm({ patientId, parameter } : Props) {
    const [error, setError] = useState<string | undefined>();
    const navigate = useNavigate();

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const formData = new FormData(event.currentTarget);
        const newParameter = {
            id: parameter?.id,
            patientId: patientId,
            name: formData.get("name") ? formData.get("name") as string : undefined,
            unit: formData.get("unit") ? formData.get("unit") as string : undefined,
            min: formData.get("min") ? parseFloat(formData.get("min") as string) : undefined,
            max: formData.get("max") ? parseFloat(formData.get("max") as string) : undefined
        };

        const response = await fetch(`${BACKEND}/api/parameter/save`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}`
            },
            body: JSON.stringify(newParameter)
        });

        if(response.status == 201)
            navigate(`/patient/${patientId}`);

        else {
            const data = await response.text();
            setError(data);
        }
    };

    return <form className="pb-5" onSubmit={handleSubmit}>
        <Input type="text" id="name" label="Name" inputValue={parameter ? parameter.name : ""}/>
        <Input type="text" id="unit" label="Unit" inputValue={parameter ? parameter.unit : ""}/>
        <Input type="number" id="min" label="Min" inputValue={parameter ? parameter.min : ""}/>
        <Input type="number" id="max" label="Max" inputValue={parameter ? parameter.max : ""}/>
        {error && <div className="text-center text-danger py-3">{error}</div>}
        <div className="d-flex flex-column align-items-end pt-3">
            <button type="submit" className="btn btn-primary col-12 col-lg-4">Save parameter</button>
        </div>
    </form>
}