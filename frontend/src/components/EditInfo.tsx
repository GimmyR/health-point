import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { BACKEND } from "../lib/url";
import type IPatient from "../interfaces/IPatient";
import EditPatientForm from "./EditPatientForm";
import useRole from "../hooks/useRole";

export default function EditInfo() {
    const {id} = useParams();
    const { isStaff } = useRole();
    const navigate = useNavigate();
    const [patient, setPatient] = useState<IPatient | undefined>();

    if(!isStaff)
        navigate("/");

    useEffect(() => {
        fetch(`${BACKEND}/api/patient-details/${id}`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => setPatient(data));
    }, []);

    return <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
        {patient && <div className="pt-5 pb-3">
            <div className="d-flex flex-column align-items-center">
                <h1 className="fs-3 text-decoration-underline">Edit patient</h1>
                <div className="col-12 col-lg-5 mt-3 mt-lg-4">
                    <EditPatientForm patient={patient}/>
                </div>
            </div>
        </div>}
    </main>
}