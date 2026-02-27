import { useParams } from "react-router-dom";

export default function EditInfo() {
    const {id} = useParams();

    return <div className="text-danger pt-5 mt-5">{`ID = ${id}`}</div>
}