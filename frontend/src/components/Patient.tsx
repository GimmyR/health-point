import { useEffect, useState } from "react";
import AccountInfo from "./AccountInfo";
import HPChart from "./HPChart";
import ParametersTable from "./ParametersTable";
import PatientInfo from "./PatientInfo";
import type IPatient from "../interfaces/IPatient";
import { BACKEND } from "../lib/url";

export default function Patient() {
    const [patient, setPatient] = useState<IPatient | undefined>();

    const fetchPatient = async () => {
        const response = await fetch(`${BACKEND}/api/patient`, { 
            headers: { 
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` 
            } 
        });

        if(response.status == 200)
            setPatient(await response.json());

        else console.log(await response.text());
    };

    useEffect(() => {
        fetchPatient();
    }, []);

    return <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
        {patient && <div className="pt-4 pb-3">
            <div className="d-flex flex-column flex-lg-row">
                <div className="col-12 col-lg-6">
                    <AccountInfo account={patient.account}/>
                </div>
                <div className="col-12 col-lg-6">
                    <PatientInfo patient={patient.details}/>
                </div>
            </div>
            <div className="d-flex flex-column mb-5">
                {patient.parameters.map(param => <HPChart key={param.id} parameter={param}/>)}
            </div>
            <div className="d-flex flex-column">
                <ParametersTable dateTimes={patient.entryDates} parameters={patient.parameters}/>
            </div>
        </div>}
    </main>
}