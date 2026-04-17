import { NavLink } from "react-router-dom";
import type { IStaffItem } from "../interfaces/IStaffItem";

type Props = {
    staffs: IStaffItem[];
};

export default function StaffsTable({ staffs } : Props) {
    return <table className="table text-center">
        <thead>
            <tr>
                <th>Username</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Gender</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            {staffs.map(staff => <tr key={staff.id}>
                <td>{staff.username}</td>
                <td>{staff.firstname}</td>
                <td>{staff.lastname}</td>
                <td>{staff.gender}</td>
                <td><NavLink to={`/staff/edit/${staff.id}`}>Edit</NavLink></td>
            </tr>)}
        </tbody>
    </table>
}