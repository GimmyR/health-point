import { useEffect, useState } from "react";
import Patient from "./Patient";
import Staff from "./Staff";
import { BACKEND } from "../lib/url";

export default function Home() {
    const [isPatient, setIsPatient] = useState(false);
    const [isStaff, setIsStaff] = useState(false);

    const verifyRole = async () => {
        await fetch(`${BACKEND}/api/is-patient`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => setIsPatient(data));
        
        await fetch(`${BACKEND}/api/is-staff`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => setIsStaff(data));
    };

    useEffect(() => {
        verifyRole();
    }, []);

    if(isPatient)
        return <Patient/>

    else if(isStaff)
        return <Staff/>

    else return null;
}