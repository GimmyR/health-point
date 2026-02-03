import { useState, type FormEvent } from "react";
import InputGroup from "./InputGroup";
import { useNavigate } from "react-router-dom";
import { BACKEND } from "../lib/url";

export default function SignIn() {
    const [error, setError] = useState<string | undefined>();
    const navigate = useNavigate();

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const formData = new FormData(e.currentTarget);
        const form = {
            username: formData.get("username") as string,
            password: formData.get("password") as string
        };

        const response = await fetch(`${BACKEND}/api/sign-in`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(form)
        });

        if(response.status == 200) {
            const data = await response.text();
            localStorage.setItem("jwtoken", data);
            navigate("/");
        } else {
            localStorage.removeItem("jwtoken");
            const data = await response.text();
            setError(data);
        }
    };

    return (
        <main id="sign-in" className="container-fluid px-0">
            <div className="d-flex flex-row justify-content-center justify-content-lg-end">
                <div className="col-12 col-lg-5 col-xl-4 col-xxl-3 min-vh-100 d-flex flex-row justify-content-center align-items-center align-items-lg-start bg-light pt-lg-5 px-lg-5">
                    <form className="col-8 col-sm-7 col-md-5 col-lg-12" onSubmit={handleSubmit}>
                        <h1 className="text-primary text-center fs-3 mb-4">Login</h1>
                        {error && <div className="alert alert-danger mb-4 py-1" role="alert">{error}</div>}
                        <InputGroup icon="person" name="username" placeholder="Username"/>
                        <InputGroup icon="lock" type="password" name="password" placeholder="Password"/>
                        <button type="submit" className="btn btn-primary col-12 rounded-0">Sign in</button>
                    </form>
                </div>
            </div>
        </main>
    );
}