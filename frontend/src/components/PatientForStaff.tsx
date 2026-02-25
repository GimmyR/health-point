import { useParams } from "react-router-dom"
import Patient from "./Patient";

export default function PatientForStaff() {
    const { id } = useParams();

    if(id)
        return <Patient id={parseInt(id)}/>

    else return null;
}