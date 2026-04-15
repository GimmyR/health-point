import { NavLink } from "react-router-dom";
import type { IAccount } from "../interfaces/IAccount";
import { useEffect, useState } from "react";
import { BACKEND } from "../lib/url";
import AccountsTable from "./AccountsTable";

export default function Admin() {
    const [accounts, setAccounts] = useState<IAccount[]>([]);
    
    const fetchAccounts = async () => {
        await fetch(`${BACKEND}/api/account/all`, { headers: { "Authorization": `Bearer ${localStorage.getItem("jwtoken")}` } })
                .then(response => response.json())
                .then(data => setAccounts(data))
                .catch(error => console.log(error));
    };

    useEffect(() => {
        fetchAccounts();
    }, []);

    return <>
        <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
            <div className="pt-5 pb-3">
                <div className="d-flex flex-column align-items-center">
                    <h1 className="text-decoration-underline fs-4 mt-lg-5">List of accounts</h1>
                    <form className="my-4 my-lg-5">
                        <div className="mb-3">
                            <input type="text" className="form-control bg-light border-0 border-bottom" placeholder="Search"/>
                        </div>
                    </form>
                    <div className="col-12 col-lg-6">
                        {accounts.length > 0 && <AccountsTable accounts={accounts}/>}
                    </div>
                </div>
            </div>
        </main>
        <div className="d-flex flex-row justify-content-end fixed-bottom pe-4 pe-lg-4 pb-4">
            <NavLink to="/staff/add" className="btn btn-dark pe-3">
                <i className="bi bi-plus-lg me-2"></i>Add staff
            </NavLink>
        </div>
    </>
}