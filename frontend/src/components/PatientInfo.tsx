type Props = {
    patient: {
        room: string,
        diagnosis: string
    }
};

export default function PatientInfo({ patient } : Props) {
    return <>
        <ul style={{ listStyleType: "none" }} className="text-lg-end px-0">
            <li>
                <span className="fw-bold">Room :</span> {patient.room}
            </li>
            <li>
                <span className="fw-bold">Diagnosis :</span> {patient.diagnosis}
            </li>
        </ul>
    </>
}