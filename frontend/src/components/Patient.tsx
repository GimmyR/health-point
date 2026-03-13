import { useEffect, useState } from "react";
import AccountInfo from "./AccountInfo";
import HPChart from "./HPChart";
import ParametersTable from "./ParametersTable";
import PatientInfo from "./PatientInfo";
import type IPatient from "../interfaces/IPatient";
import { BACKEND } from "../lib/url";
import AddParameterOrEntry from "./AddParameterOrEntryButton";

type Props = {
    isStaff?: boolean,
    id?: number
};

export default function Patient({ isStaff, id } : Props) {
    const [patient, setPatient] = useState<IPatient | undefined>();

    const fetchPatient = async () => {
        const route = id ? "patients/" + id : "patient";
        const response = await fetch(`${BACKEND}/api/${route}`, { 
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

    return <>
        <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
            {patient && <div className="pt-4 pb-3">
                <div className="d-flex flex-column flex-lg-row">
                    <div className="col-12 col-lg-6">
                        <AccountInfo isStaff={isStaff} account={patient.account}/>
                    </div>
                    <div className="col-12 col-lg-6">
                        <PatientInfo isStaff={isStaff} id={id} room={patient.room} diagnosis={patient.diagnosis}/>
                    </div>
                </div>
                {patient.parameters.length > 0 && <div className="d-flex flex-column mb-5">
                    {patient.parameters.map(param => <HPChart key={param.id} parameter={param}/>)}
                </div>}
                <div className="d-flex flex-column mb-5">
                    <ParametersTable isStaff={isStaff} dateTimes={patient.entryDates} parameters={patient.parameters}/>
                </div>
            </div>}
        </main>
        {isStaff && <div className="d-flex flex-row justify-content-end fixed-bottom pe-4 pe-lg-4 pb-4">
            <AddParameterOrEntry/>
        </div>}
    </>
}