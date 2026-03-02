import { useEffect, useState } from "react"
import type { IPatientItem } from "../interfaces/IPatientItem";
import { BACKEND } from "../lib/url";
import PatientsTable from "./PatientsTable";
import { NavLink } from "react-router-dom";

export default function Staff() {
    const [patients, setPatients] = useState<IPatientItem[]>([]);

    const fetchPatients = async () => {
        await fetch(`${BACKEND}/api/patients`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
                .then(response => response.json())
                .then(data => setPatients(data))
                .catch(error => console.log(error));
    };

    useEffect(() => {
        fetchPatients();
    }, []);

    return <>
        <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
            <div className="pt-5 pb-3">
                <div className="d-flex flex-column align-items-center">
                    <h1 className="text-decoration-underline fs-4 mt-lg-5">List of patients</h1>
                    <form className="my-4 my-lg-5">
                        <div className="mb-3">
                            <input type="text" className="form-control bg-light border-0 border-bottom" placeholder="Search"/>
                        </div>
                    </form>
                    <div className="col-12 col-lg-6">
                        {patients.length > 0 && <PatientsTable patients={patients}/>}
                    </div>
                </div>
            </div>
        </main>
        <NavLink to="/add-patient" className="btn btn-dark position-absolute bottom-0 end-0 me-4 me-lg-5 mb-4">
            <i className="bi bi-plus-lg me-2"></i>Add patient
        </NavLink>
    </>
}