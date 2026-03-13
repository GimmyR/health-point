import { useNavigate } from "react-router-dom";
import { BACKEND } from "../lib/url";
import EditPatientForm from "./EditPatientForm";
import { useEffect } from "react";

export default function AddPatient() {
    const navigate = useNavigate();

    const checkIfIsStaff = async () => {
        await fetch(`${BACKEND}/api/is-staff`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => {
                if(!data)
                    navigate("/");
            });
    };

    useEffect(() => {
        checkIfIsStaff();
    }, []);

    return <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
        <div className="pt-5 pb-3">
            <div className="d-flex flex-column align-items-center">
                <h1 className="fs-3 text-decoration-underline">Add patient</h1>
                <div className="col-12 col-lg-5 mt-3 mt-lg-4">
                    <EditPatientForm/>
                </div>
            </div>
        </div>
    </main>
}