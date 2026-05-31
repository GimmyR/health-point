type Props = {
    type: string,
    id: string,
    label: string
};

export default function EditPasswordInput({ type, id, label } : Props) {
    return <div className="d-flex flex-column flex-lg-row justify-content-between align-items-lg-center mb-3">
        <div className="me-lg-5">
            <label htmlFor={id} className="form-label">{label}</label>
        </div>
        <div className="col-lg-7">
            <input type={type} id={id} className="form-control text-secondary" name={id} step="any"/>
        </div>
    </div>
}