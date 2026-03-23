type Props = {
    type: string,
    id: string,
    label: string,
    inputValue: string | number
};

export default function Input({ type, id, label, inputValue } : Props) {
    return <>
        <label htmlFor={id} className="form-label">{label}</label>
        <input type={type} id={id} className="form-control text-secondary" name={id} defaultValue={inputValue}/>
    </>
}