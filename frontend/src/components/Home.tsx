import Patient from "./Patient";
import Staff from "./Staff";
import useRole from "../hooks/useRole";
import Admin from "./Admin";

export default function Home() {
    const { isPatient, isStaff, isAdmin } = useRole();

    if(isPatient)
        return <Patient/>

    else if(isStaff && !isAdmin)
        return <Staff/>

    else if(isStaff && isAdmin)
        return <Admin/>

    else return null;
}