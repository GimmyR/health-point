import { NavLink, useNavigate, useParams } from "react-router-dom";
import useRole from "../hooks/useRole";
import AddStaffForm from "./AddStaffForm";
import { useEffect, useState } from "react";
import type { IStaff } from "../interfaces/IStaff";
import { BACKEND } from "../lib/url";

export default function EditStaff() {
    const { id } = useParams();
    const { isAdmin } = useRole();
    const navigate = useNavigate();
    const [staff, setStaff] = useState<IStaff | undefined>();

    if(isAdmin != undefined && !isAdmin)
        navigate("/");

    useEffect(() => {
        fetch(`${BACKEND}/api/staffs/${id}`, {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}`
            }
        }).then(res => res.json())
            .then(data => setStaff(data))
            .catch(err => console.error(err));
    }, []);

    return <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
        <div className="pt-5 pb-3">
            <div className="d-flex flex-column align-items-center">
                <div className="d-flex flex-row align-items-center">
                    <h1 className="fs-3 text-decoration-underline">Edit staff</h1>
                    {staff && <NavLink to={`/staff/remove/${staff.id}`} className="text-decoration-none ms-3">
                        <i className="bi bi-trash fs-5 text-danger"></i>
                    </NavLink>}
                </div>
                <div className="col-12 col-lg-5 mt-3 mt-lg-4">
                    <AddStaffForm staff={staff}/>
                </div>
            </div>
        </div>
    </main>
}