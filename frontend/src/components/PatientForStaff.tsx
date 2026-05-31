import { useNavigate, useParams } from "react-router-dom"
import Patient from "./Patient";
import useRole from "../hooks/useRole";

export default function PatientForStaff() {
    const { id } = useParams();
    const { isStaff } = useRole();
    const navigate = useNavigate();

    if(isStaff != undefined && !isStaff)
        navigate("/");

    if(isStaff != undefined && isStaff && id != undefined)
        return <Patient isStaff={isStaff} id={parseInt(id)}/>

    else return null;
}