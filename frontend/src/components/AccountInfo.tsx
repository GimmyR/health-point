import type Account from "../interfaces/Account";

type Props = {
    isStaff?: boolean,
    account: Account
};

export default function AccountInfo({ isStaff, account } : Props) {
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
            {isStaff && <li>
                <span className="fw-bold">Contact :</span> {account.contact}
            </li>}
        </ul>
    </>
}