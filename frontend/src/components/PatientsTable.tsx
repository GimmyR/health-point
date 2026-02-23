import type { IPatientItem } from "../interfaces/IPatientItem";

type Props = {
    patients: IPatientItem[]
};

export default function PatientsTable({ patients } : Props) {
    return <table className="table text-center">
        <thead>
            <tr>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Gender</th>
                <th>Room</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            {patients.map(patient => <tr key={patient.id}>
                <td>{patient.firstname}</td>
                <td>{patient.lastname}</td>
                <td>{patient.gender}</td>
                <td>{patient.room}</td>
                <td><a href="#">Details</a></td>
            </tr>)}
        </tbody>
    </table>
}