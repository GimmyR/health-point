import { useEffect } from "react";
import { useNavigate, useParams, useSearchParams } from "react-router-dom"
import { BACKEND } from "../lib/url";

export default function EditEntry() {
    const { id } = useParams();
    const [ searchParams ] = useSearchParams();
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`${BACKEND}/api/is-staff`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => {
                if(!data)
                    navigate("/");
            });
    }, []);

    return <div className="text-danger mt-5 pt-5">{ `${id} => ${searchParams.get("dt")}` }</div>
}