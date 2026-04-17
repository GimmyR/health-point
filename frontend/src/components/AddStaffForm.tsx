import { useState, type FormEvent } from "react";
import Input from "./Input";
import { BACKEND } from "../lib/url";
import { NavLink, useNavigate } from "react-router-dom";
import type { IStaff } from "../interfaces/IStaff";

type Props = {
    staff?: IStaff
};

const genders = [ "Female", "Male" ];

export default function AddStaffForm({ staff } : Props) {
    const [error, setError] = useState<string | undefined>();
    const navigate = useNavigate();
    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const newStaff = {
            id: staff?.id,
            account: {
                username: formData.get("username") as string,
                password: formData.get("password") as string,
                firstname: formData.get("firstname") as string,
                lastname: formData.get("lastname") as string,
                gender: formData.get("gender") as string,
                dateOfBirth: formData.get("date-of-birth") as string,
                address: formData.get("address") as string,
                contact: formData.get("contact") as string
            }
        };
        
        const response = await fetch(`${BACKEND}/api/staff/save`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${localStorage.getItem("jwtoken")}`
            },
            body: JSON.stringify(newStaff)
        });

        const data = await response.text();
        console.log(response);
        
        if(response.status == 201)
            navigate("/");
        
        else setError(data);
    };

    return <form onSubmit={handleSubmit}>
        <Input type="text" id="username" label="Username" inputValue={staff ? staff.account.username : ""}/>
        {!staff && <Input type="text" id="password" label="Password" inputValue=""/>}
        <Input type="text" id="firstname" label="Firstname" inputValue={staff ? staff.account.firstname : ""}/>
        <Input type="text" id="lastname" label="Lastname" inputValue={staff ? staff.account.lastname : ""}/>
        <div className="d-flex flex-column flex-lg-row justify-content-between align-items-lg-center mb-3">
            <div className="me-lg-4">
                <label htmlFor="gender" className="form-label">Gender</label>
            </div>
            <div className="col-lg-9">
                <select id="gender" className="form-select text-secondary" name="gender" defaultValue={staff?.account.gender}>
                    {genders.map(gender => <option key={gender} value={gender}>{gender}</option>)}
                </select>
            </div>
        </div>
        <Input type="date" id="date-of-birth" label="Date of birth" inputValue={staff ? staff.account.dateOfBirth : ""}/>
        <Input type="text" id="address" label="Address" inputValue={staff ? staff.account.address : ""}/>
        <Input type="text" id="contact" label="Contact" inputValue={staff ? staff.account.contact : ""}/>
        {error && <div className="text-center text-danger py-3">{error}</div>}
        <div className="d-flex flex-column flex-lg-row justify-content-lg-end pt-3">
            {staff && <NavLink to={`/staff/remove/${staff.id}`} className="btn btn-danger col-12 col-lg-auto rounded-0 mb-2 mb-lg-0 me-lg-2">Remove</NavLink>}
            <button type="submit" className="btn btn-primary col-12 col-lg-3 rounded-0">Submit</button>
        </div>
    </form>
}