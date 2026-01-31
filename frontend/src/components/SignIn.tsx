import type { FormEvent } from "react";
import InputGroup from "./InputGroup";

export default function SignIn() {
    const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
    };

    return (
        <main className="container-fluid px-0">
            <div className="d-flex flex-row justify-content-center justify-content-lg-end">
                <div className="col-12 col-lg-5 col-xl-4 col-xxl-3 min-vh-100 d-flex flex-row justify-content-center align-items-center align-items-lg-start bg-light pt-lg-5 px-lg-5">
                    <form className="col-8 col-sm-7 col-md-5 col-lg-12" onSubmit={handleSubmit}>
                        <h1 className="text-primary text-center fs-3 mb-4">Login</h1>
                        <InputGroup icon="person" placeholder="Username"/>
                        <InputGroup icon="lock" placeholder="Password"/>
                        <button type="submit" className="btn btn-primary col-12 rounded-0">Sign in</button>
                    </form>
                </div>
            </div>
        </main>
    );
}