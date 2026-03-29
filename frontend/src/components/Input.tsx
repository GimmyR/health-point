type Props = {
    type: string,
    id: string,
    label: string,
    inputValue: string | number
};

export default function Input({ type, id, label, inputValue } : Props) {
    return <div className="d-flex flex-column flex-lg-row justify-content-between align-items-lg-center mb-3">
        <div className="me-lg-4">
            <label htmlFor={id} className="form-label">{label}</label>
        </div>
        <div className="col-lg-9">
            <input type={type} id={id} className="form-control text-secondary" name={id} defaultValue={inputValue} step="any"/>
        </div>
    </div>
}