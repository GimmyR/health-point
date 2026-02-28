import { useNavigate, useParams } from "react-router-dom"
import Patient from "./Patient";
import { useEffect, useState } from "react";
import { BACKEND } from "../lib/url";

export default function PatientForStaff() {
    const { id } = useParams();
    const [isStaff, setIsStaff] = useState(false);
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`${BACKEND}/api/is-staff`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => {
                setIsStaff(data);

                if(!data)
                    navigate("/");
            });
    }, []);

    if(isStaff && id != undefined)
        return <Patient isStaff={isStaff} id={parseInt(id)}/>

    else return null;
}