type Props = {
    account: {
        firstname: string,
        lastname: string,
        gender: string,
        dateOfBirth: string,
        address: string
    }
};

export default function AccountInfo({ account } : Props) {
    return <>
        <ul style={{ listStyleType: "none" }} className="px-0">
            <li>
                <span className="fw-bold">Name :</span> {account.firstname + " " + account.lastname}
            </li>
            <li>
                <span className="fw-bold">Gender :</span> {account.gender}
            </li>
            <li>
                <span className="fw-bold">Date of birth :</span> {account.dateOfBirth}
            </li>
            <li>
                <span className="fw-bold">Address :</span> {account.address}
            </li>
        </ul>
    </>
}