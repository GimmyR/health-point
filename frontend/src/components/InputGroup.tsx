type Props = {
    icon: string,
    type?: string,
    name: string,
    placeholder: string
};

export default function InputGroup({ icon, type, name, placeholder } : Props) {
    return (
        <div className="input-group mb-3">
            <span className="input-group-text rounded-0">
                <i className={`bi bi-${icon}`}></i>
            </span>
            <input type={type == "password" ? type : "text"} className="form-control rounded-0" name={name} placeholder={`${placeholder}`}/>
        </div>
    );
}