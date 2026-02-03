import { Outlet, useMatch } from "react-router-dom";
import Header from "./Header";

export default function Root() {
    const isIndex = useMatch("/");
    const isSignIn = useMatch("/sign-in");

    return <>
        {!isSignIn && <Header/>}
        <div>
            {isIndex && <h1>Welcome !</h1>}
            <Outlet/>
        </div>
    </>
}