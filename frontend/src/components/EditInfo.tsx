import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { BACKEND } from "../lib/url";

export default function EditInfo() {
    const {id} = useParams();
    const navigate = useNavigate();

    useEffect(() => {
        fetch(`${BACKEND}/api/is-staff`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => {
                if(!data)
                    navigate("/");
            });
    }, []);

    return <div className="text-danger pt-5 mt-5">{`ID = ${id}`}</div>
}