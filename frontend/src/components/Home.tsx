import Patient from "./Patient";
import Staff from "./Staff";
import useRole from "../hooks/useRole";

export default function Home() {
    const { isPatient, isStaff } = useRole();

    if(isPatient)
        return <Patient/>

    else if(isStaff)
        return <Staff/>

    else return null;
}