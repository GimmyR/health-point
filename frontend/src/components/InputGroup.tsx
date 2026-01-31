type Props = {
    icon: string,
    placeholder: string
};

export default function InputGroup({ icon, placeholder } : Props) {
    return (
        <div className="input-group mb-3">
            <span className="input-group-text rounded-0">
                <i className={`bi bi-${icon}`}></i>
            </span>
            <input type="text" className="form-control rounded-0" placeholder={`${placeholder}`}/>
        </div>
    );
}