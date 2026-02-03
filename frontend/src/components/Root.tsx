import { useEffect } from "react";
import { Outlet, useMatch, useNavigate } from "react-router-dom";

export default function Root() {
    const isIndex = useMatch("/");
    const isSignIn = useMatch("/sign-in");
    const navigate = useNavigate();

    useEffect(() => {
        const jwtoken = localStorage.getItem("jwtoken");

        if(!jwtoken)
            navigate("/sign-in");
    }, []);

    return <>
        {!isSignIn && <header>
            HEADER
        </header>}
        <div>
            {isIndex && <h1>Welcome !</h1>}
            <Outlet/>
        </div>
    </>
}