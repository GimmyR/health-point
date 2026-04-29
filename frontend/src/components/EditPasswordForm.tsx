import { useState, type FormEvent } from "react";
import EditPasswordInput from "./EditPasswordInput";
import useRole from "../hooks/useRole";
import { useNavigate } from "react-router-dom";

type Props = {
    accountId: number
};

export default function EditPasswordForm({ accountId } : Props) {
    const { isAdmin } = useRole();
    const navigate = useNavigate();
    const [error, setError] = useState<string | undefined>();

    const handleSubmit = (event: FormEvent<HTMLFormElement>) => {
        setError(undefined);
        event.preventDefault();
        const formData = new FormData(event.currentTarget);

        if(formData.get("new-password") != formData.get("confirm-new-password"))
            setError("New password and confirm new password don't have the same value");

        else {

        }
    };

    if(isAdmin != undefined && !isAdmin)
        navigate("/");

    return (
        <>
            <form onSubmit={handleSubmit}>
                <EditPasswordInput type="password" id="old-password" label="Old password"/>
                <EditPasswordInput type="password" id="new-password" label="New password"/>
                <EditPasswordInput type="password" id="confirm-new-password" label="Confirm new password"/>
                {error && <div className="text-center text-danger py-3">{error}</div>}
                <div className="d-flex flex-column flex-lg-row justify-content-lg-end pt-3">
                    <button type="submit" className="btn btn-primary col-12 col-lg-3 rounded-0">Submit</button>
                </div>
            </form>
        </>
    );
}