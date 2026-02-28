import { NavLink } from "react-router-dom";

type Props = {
    isStaff?: boolean,
    id?: number,
    patient: {
        room: string,
        diagnosis: string
    }
};

export default function PatientInfo({ isStaff, id, patient } : Props) {
    return <>
        <ul style={{ listStyleType: "none" }} className="text-lg-end px-0 mb-2">
            <li>
                <span className="fw-bold">Room :</span> {patient.room}
            </li>
            <li>
                <span className="fw-bold">Diagnosis :</span> {patient.diagnosis}
            </li>
        </ul>
        {isStaff && id && <div className="d-flex flex-row justify-content-lg-end mb-3 mb-lg-0">
            <NavLink to={`/edit-info/${id}`} className="btn btn-primary py-1">
                <i className="bi bi-pencil-fill me-2"></i>Edit
            </NavLink>
        </div>}
    </>
}