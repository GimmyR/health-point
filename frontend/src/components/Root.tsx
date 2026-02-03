import { Outlet, useMatch } from "react-router-dom";
import Header from "./Header";
import Home from "./Home";

export default function Root() {
    const isIndex = useMatch("/");
    const isSignIn = useMatch("/sign-in");

    return <>
        {!isSignIn && <Header/>}
        <div>
            {isIndex && <Home/>}
            <Outlet/>
        </div>
    </>
}