import { useState, type FormEvent } from "react";
import type IPatient from "../interfaces/IPatient";
import Input from "./Input";
import { BACKEND } from "../lib/url";
import { useNavigate } from "react-router-dom";

type Props = {
    patient?: IPatient
};

const genders = [ "Female", "Male" ];

export default function EditPatientForm({ patient } : Props) {
    const [error, setError] = useState<string | undefined>();
    const navigate = useNavigate();
    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const newPatient = {
            id: patient?.id,
            room: formData.get("room") as string,
            diagnosis: formData.get("diagnosis") as string,
            account: {
                firstname: formData.get("firstname") as string,
                lastname: formData.get("lastname") as string,
                gender: formData.get("gender") as string,
                dateOfBirth: formData.get("date-of-birth") as string,
                address: formData.get("address") as string,
                contact: formData.get("contact") as string
            }
        };
        
        const response = await fetch(`${BACKEND}/api/save-patient`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}`
            },
            body: JSON.stringify(newPatient)
        });

        const data = await response.text();
        console.log(response);
        
        if(response.status == 201)
            navigate(`/patient/${data}`);
        
        else setError(data);
    };

    return <form onSubmit={handleSubmit}>
        <Input type="text" id="firstname" label="Firstname" inputValue={patient ? patient.account.firstname : ""}/>
        <Input type="text" id="lastname" label="Lastname" inputValue={patient ? patient.account.lastname : ""}/>
        <div className="d-flex flex-column flex-lg-row justify-content-between align-items-lg-center mb-3">
            <div className="me-lg-4">
                <label htmlFor="gender" className="form-label">Gender</label>
            </div>
            <div className="col-lg-9">
                <select id="gender" className="form-select text-secondary" name="gender" defaultValue={patient?.account.gender}>
                    {genders.map(gender => <option key={gender} value={gender}>{gender}</option>)}
                </select>
            </div>
        </div>
        <Input type="date" id="date-of-birth" label="Date of birth" inputValue={patient ? patient.account.dateOfBirth : ""}/>
        <Input type="text" id="address" label="Address" inputValue={patient ? patient.account.address : ""}/>
        <Input type="text" id="contact" label="Contact" inputValue={patient ? patient.account.contact : ""}/>
        <Input type="text" id="room" label="Room" inputValue={patient ? patient.room : ""}/>
        <Input type="text" id="diagnosis" label="Diagnosis" inputValue={patient ? patient.diagnosis : ""}/>
        {error && <div className="text-center text-danger py-3">{error}</div>}
        <div className="d-flex flex-column align-items-end pt-3">
            <button type="submit" className="btn btn-primary col-12 col-lg-4">Save informations</button>
        </div>
        <div className="toast align-items-center" role="alert" aria-live="assertive" aria-atomic="true">
            <div className="d-flex">
                <div className="toast-body">
                    Hello, world! This is a toast message.
                </div>
                <button type="button" className="btn-close me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
        </div>
    </form>
}