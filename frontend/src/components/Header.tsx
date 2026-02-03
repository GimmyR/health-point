import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { BACKEND } from "../lib/url";

export default function Header() {
    const navigate = useNavigate();

    const isSignedIn = (token: string) => {
        fetch(`${BACKEND}/api/signed-in`, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        }).then(r => {
            if(r.status != 200)
                navigate("/sign-in");
        });
    };

    useEffect(() => {
        const jwtoken = localStorage.getItem("jwtoken");

        if(!jwtoken)
            navigate("/sign-in");
        
        else isSignedIn(jwtoken);
    }, []);

    return (
        <header className="container-fluid bg-light py-2">
            <div className="d-flex flex-row justify-content-between align-items-center fs-3">
                <div className="d-flex flex-row">
                    <a href="#" className="text-dark text-decoration-none fw-bold">HealthPoint</a>
                </div>
                <button type="button" className="btn btn-dark">Sign out</button>
            </div>
        </header>
    );
}