import AccountInfo from "./AccountInfo";
import HPChart from "./HPChart";
import PatientInfo from "./PatientInfo";

export default function Patient() {
    const patient = {
        account: {
            firstname: "John",
            lastname: "Doe",
            gender: "Male",
            dateOfBirth: "1996-11-11",
            address: "Itaosy"
        },

        details: {
            room: "Room 216",
            diagnosis: "Mental illness"
        },

        parameters: [
            {
                id: 1,
                name: "Weight",
                unit: "Kg",
                min: 0,
                max: 200,
                entries: [
                    {
                        dateTime: "2026-01-01 06:00:00",
                        value: 102
                    },
                    {
                        dateTime: "2026-01-02 06:00:00",
                        value: 90
                    }
                ]
            },
            {
                id: 2,
                name: "Temperature",
                unit: "*C",
                min: 35,
                max: 40,
                entries: [
                    {
                        dateTime: "2026-01-01 06:00:00",
                        value: 36.5
                    },
                    {
                        dateTime: "2026-01-01 18:00:00",
                        value: 35.7
                    },
                    {
                        dateTime: "2026-01-02 06:00:00",
                        value: 35.5
                    },
                    {
                        dateTime: "2026-01-02 18:00:00",
                        value: 36.2
                    }
                ]
            }
        ]
    };

    return <main className="container-fluid container-lg min-vh-100 px-4 pt-5 bg-light">
        <div className="pt-4">
            <div className="d-flex flex-column flex-lg-row">
                <div className="col-12 col-lg-6">
                    <AccountInfo account={patient.account}/>
                </div>
                <div className="col-12 col-lg-6">
                    <PatientInfo patient={patient.details}/>
                </div>
            </div>
            <div className="d-flex flex-column">
                {patient.parameters.map(param => <HPChart key={param.id} parameter={param}/>)}
            </div>
            <div className="d-flex flex-column">
                Tables
            </div>
        </div>
    </main>
}