import { useParams, useSearchParams } from "react-router-dom"

export default function EditEntry() {
    const { id } = useParams();
    const [ searchParams ] = useSearchParams();

    return <div className="text-danger mt-5 pt-5">{ `${id} => ${searchParams.get("dt")}` }</div>
}