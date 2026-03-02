export default function AddParameterOrEntry() {
    return <div className="dropdown dropstart">
        <button type="button" className="btn btn-dark pe-3" data-bs-toggle="dropdown" aria-expanded="false">
            <i className="bi bi-plus-lg me-2"></i>Add
        </button>
        <ul className="dropdown-menu dropdown-menu-dark">
            <li><a className="dropdown-item" href="#"><i className="bi bi-sliders2-vertical me-2"></i>Parameter</a></li>
            <li><a className="dropdown-item" href="#"><i className="bi bi-graph-up-arrow me-2"></i>Entry</a></li>
        </ul>
    </div>
}