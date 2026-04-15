import { NavLink } from "react-router-dom";
import type { IAccount } from "../interfaces/IAccount";

type Props = {
    accounts: IAccount[]
};

export default function AccountsTable({ accounts } : Props) {
    return <table className="table text-center">
        <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Gender</th>
                <th>Role(s)</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            {accounts.map(account => <tr key={account.id}>
                <td>{account.firstname}</td>
                <td>{account.lastname}</td>
                <td>{account.gender}</td>
                <td>{account.roles.map(role => role.name).join(", ")}</td>
                <td><NavLink to={`/account/${account.id}`}>Details</NavLink></td>
            </tr>)}
        </tbody>
    </table>
}