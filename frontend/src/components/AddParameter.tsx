import { useNavigate, useParams } from "react-router-dom";
import AddParameterForm from "./AddParameterForm";
import useRole from "../hooks/useRole";

export default function AddParameter() {
    const { patientId } = useParams();
    const { isStaff } = useRole();
    const navigate = useNavigate();

    if(!isStaff)
        navigate("/");

    if(patientId)
        return <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
            <div className="pt-5 pb-3">
                <div className="d-flex flex-column align-items-center">
                    <h1 className="fs-3 text-decoration-underline">Add parameter</h1>
                    <div className="col-12 col-lg-4 mt-3 mt-lg-4">
                        <AddParameterForm patientId={parseInt(patientId)}/>
                    </div>
                </div>
            </div>
        </main>

    else return null;
}