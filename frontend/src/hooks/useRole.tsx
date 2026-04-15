import { useEffect, useState } from "react";
import { BACKEND } from "../lib/url";

export default function useRole() {
    const [isPatient, setIsPatient] = useState();
    const [isStaff, setIsStaff] = useState();
    const [isAdmin, setIsAdmin] = useState();

    useEffect(() => {
        fetch(`${BACKEND}/api/is-patient`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => setIsPatient(data));

        fetch(`${BACKEND}/api/is-staff`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => setIsStaff(data));

        fetch(`${BACKEND}/api/is-admin`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => setIsAdmin(data));
    }, []);

    return { isPatient, isStaff, isAdmin };
}