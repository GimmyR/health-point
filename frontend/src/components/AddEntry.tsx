import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import AddEntryForm from "./AddEntryForm";
import useRole from "../hooks/useRole";

export default function AddEntry() {
    const { isStaff } = useRole();
    const { patientId } = useParams();
    const [searchParams] = useSearchParams();
    const datetime = searchParams.get("datetime") as string;
    const parameterId = parseInt(searchParams.get("parameter") as string);
    const navigate = useNavigate();

    if(!isStaff)
        navigate("/");

    if(patientId)
        return (
            <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
                <div className="pt-5 pb-3">
                    <div className="d-flex flex-column align-items-center">
                        <h1 className="fs-3 text-decoration-underline">Add entry</h1>
                        <div className="col-12 col-lg-4 mt-3 mt-lg-4">
                            <AddEntryForm patientId={parseInt(patientId)} datetime={datetime} parameterId={parameterId}/>
                        </div>
                    </div>
                </div>
            </main>
        );

    else return null;
}