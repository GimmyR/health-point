import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import Root from './components/Root.tsx';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.min.css';
import './main.css';
import SignIn from './components/SignIn.tsx';
import PatientForStaff from './components/PatientForStaff.tsx';
import EditEntry from './components/EditEntry.tsx';
import EditInfo from './components/EditInfo.tsx';

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root/>,
        errorElement: <div>Error page</div>,
        children: [
            {
                path: "sign-in",
                element: <SignIn/>
            },
            {
                path: "patient/:id",
                element: <PatientForStaff/>
            },
            {
                path: "edit-info/:id",
                element: <EditInfo/>
            },
            {
                path: "edit-entry/:id",
                element: <EditEntry/>
            }
        ]
    }
]);

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <RouterProvider router={router}/>
    </StrictMode>,
)
