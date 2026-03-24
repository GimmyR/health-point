import { useSearchParams } from "react-router-dom";
import AddParameterForm from "./AddParameterForm";
import { useEffect, useState } from "react";
import type Parameter from "../interfaces/Parameter";
import { BACKEND } from "../lib/url";

export default function EditParameter() {
    const [searchParams] = useSearchParams();
    const parameterId = searchParams.get("id");
    const patientId = searchParams.get("patient");
    const [parameter, setParameter] = useState<Parameter>();

    useEffect(() => {
        fetch(`${BACKEND}/api/parameters/${parameterId}`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => setParameter(data))
            .catch(error => console.error(error));
    }, []);

    if(patientId && parameter)
        return <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
            <div className="pt-5 pb-3">
                <div className="d-flex flex-column align-items-center">
                    <h1 className="fs-3 text-decoration-underline">Edit parameter</h1>
                    <div className="col-12 col-lg-4 mt-3 mt-lg-4">
                        <AddParameterForm patientId={parseInt(patientId)} parameter={parameter}/>
                    </div>
                </div>
            </div>
        </main>

    else return null;
}