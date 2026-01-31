import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Root from './components/Root.tsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.min.css';
import './main.css';
import SignIn from './components/SignIn.tsx';

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root/>,
        errorElement: <div>Error page</div>,
        children: [
            {
                path: "sign-in",
                element: <SignIn/>
            }
        ]
    }
]);

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <RouterProvider router={router}/>
    </StrictMode>,
)
