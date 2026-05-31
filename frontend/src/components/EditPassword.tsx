import { useParams } from "react-router-dom";
import EditPasswordForm from "./EditPasswordForm";

export default function EditPassword() {
    const { accountId } = useParams();

    if(accountId)
        return (
            <>
                <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
                    <div className="pt-5 pb-3">
                        <div className="d-flex flex-column align-items-center">
                            <div className="d-flex flex-row align-items-center">
                                <h1 className="fs-3 text-decoration-underline">Edit password</h1>
                            </div>
                            <div className="col-12 col-lg-5 mt-3 mt-lg-4">
                                <EditPasswordForm accountId={parseInt(accountId)}/>
                            </div>
                        </div>
                    </div>
                </main>
            </>
        );

    else return null;
}