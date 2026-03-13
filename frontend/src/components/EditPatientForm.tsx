import type { FormEvent } from "react";
import type IPatient from "../interfaces/IPatient";
import Input from "./Input";
import { BACKEND } from "../lib/url";
import { useNavigate } from "react-router-dom";

type Props = {
    patient?: IPatient
};

const genders = [ "Female", "Male" ];

export default function EditPatientForm({ patient } : Props) {
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
        
        await fetch(`${BACKEND}/api/save-patient`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}`
            },
            body: JSON.stringify(newPatient)
        }).then(response => response.text())
            .then(data => navigate(`/patient/${data}`))
            .catch(error => console.error(error));
    };

    return <form onSubmit={handleSubmit} className="pb-5">
        <div className="mb-3">
            <Input type="text" id="firstname" label="Firstname" inputValue={patient ? patient.account.firstname : ""}/>
        </div>
        <div className="mb-3">
            <Input type="text" id="lastname" label="Lastname" inputValue={patient ? patient.account.lastname : ""}/>
        </div>
        <div className="mb-3">
            <label htmlFor="gender" className="form-label">Gender</label>
            <select id="gender" className="form-select text-secondary" name="gender" defaultValue={patient?.account.gender}>
                {genders.map(gender => <option key={gender} value={gender}>{gender}</option>)}
            </select>
        </div>
        <div className="mb-3">
            <Input type="date" id="date-of-birth" label="Date of birth" inputValue={patient ? patient.account.dateOfBirth : ""}/>
        </div>
        <div className="mb-3">
            <Input type="text" id="address" label="Address" inputValue={patient ? patient.account.address : ""}/>
        </div>
        <div className="mb-3">
            <Input type="text" id="contact" label="Contact" inputValue={patient ? patient.account.contact : ""}/>
        </div>
        <div className="mb-3">
            <Input type="text" id="room" label="Room" inputValue={patient ? patient.room : ""}/>
        </div>
        <div className="mb-4 mb-lg-5">
            <Input type="text" id="diagnosis" label="Diagnosis" inputValue={patient ? patient.diagnosis : ""}/>
        </div>
        <div className="d-flex flex-row justify-content-lg-end">
            <button type="submit" className="btn btn-primary col-12 col-lg-4">Save informations</button>
        </div>
    </form>
}