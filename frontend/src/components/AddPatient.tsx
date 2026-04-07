import { useNavigate } from "react-router-dom";
import EditPatientForm from "./EditPatientForm";
import useRole from "../hooks/useRole";

export default function AddPatient() {
    const { isStaff } = useRole();
    const navigate = useNavigate();

    if(!isStaff)
        navigate("/");

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