import { createBrowserRouter, NavLink, Outlet, RouterProvider, useMatch } from "react-router-dom";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root/>,
        errorElement: <div>Error page</div>,
        children: [
            {
                path: "contact",
                element: <div>Contact page</div>
            },
            {
                path: "about",
                element: <div>About page</div>
            }
        ]
    }
]);

function Root() {
    const isIndex = useMatch("/");

    return <>
        <header>
            <nav>
                <NavLink to="/">Home</NavLink>
                <NavLink to="/contact">Contact</NavLink>
                <NavLink to="/about">About</NavLink>
            </nav>
        </header>
        <div>
            {isIndex && <h1>Welcome !</h1>}
            <Outlet/>
        </div>
    </>
}

export default function App() {
    return <RouterProvider router={router}/>
}