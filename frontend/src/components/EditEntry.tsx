import { useEffect, useState } from "react";
import { useNavigate, useParams, useSearchParams } from "react-router-dom"
import { BACKEND } from "../lib/url";
import AddEntryForm from "./AddEntryForm";
import type ParameterEntry from "../interfaces/ParameterEntry";

export default function EditEntry() {
    const { id } = useParams();
    const [searchParams] = useSearchParams();
    const navigate = useNavigate();
    const [entry, setEntry] = useState<ParameterEntry | undefined>();

    const isStaff = async () => {
        await fetch(`${BACKEND}/api/is-staff`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
            .then(response => response.json())
            .then(data => {
                if(!data)
                    navigate("/");
            });
    };

    const fetchEntry = async () => {
        await fetch(`${BACKEND}/api/entries/${id}`, {
            headers: {
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}`
            }
        }).then(response => response.json())
            .then(data => setEntry(data))
            .catch(error => console.log(error));
    };

    useEffect(() => {
        isStaff();
        fetchEntry();
    }, []);

    return (
        <>
            {entry && <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
                <div className="pt-5 pb-3">
                    <div className="d-flex flex-column align-items-center">
                        <h1 className="fs-3 text-decoration-underline">Edit entry</h1>
                        <div className="col-12 col-lg-4 mt-3 mt-lg-4">
                            <AddEntryForm patientId={parseInt(searchParams.get("patient") as string)} entry={entry}/>
                        </div>
                    </div>
                </div>
            </main>}
        </>
    );
}