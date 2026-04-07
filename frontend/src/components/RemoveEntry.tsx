import { useNavigate, useParams } from "react-router-dom";
import { BACKEND } from "../lib/url";
import useRole from "../hooks/useRole";

export default function RemoveEntry() {
    const { isStaff } = useRole();
    const { id } = useParams();
    const navigate = useNavigate();

    if(!isStaff)
        navigate("/");

    const removeEntry = async () => {
        const res = await fetch(`${BACKEND}/api/entry/remove/${id}`, {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}`
            }
        });

        const data = await res.text();

        if(res.status == 201)
            navigate(`/patient/${data}`);

        else navigate(-1);
    };

    return (
        <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
            <div className="pt-5 pb-3">
                <div className="d-flex flex-column align-items-center">
                    <h1 className="fs-3 text-center">Do you really want to remove this entry ?</h1>
                    <div className="d-flex flex-row justify-content-center col-12 col-lg-5 mt-3 mt-lg-4">
                        <div className="col-4 col-lg-3 pe-1">
                            <button type="button" onClick={() => navigate(-1)} className="btn btn-secondary col-12">No</button>
                        </div>
                        <div className="col-4 col-lg-3 ps-1">
                            <button type="button" onClick={removeEntry} className="btn btn-danger col-12">Yes</button>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    );
}