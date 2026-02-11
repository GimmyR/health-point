import { useState } from "react"
import LineChart from "./LineChart";
import BarChart from "./BarChart";
import type Parameter from "../interfaces/Parameter";
import ChartTypeSelect from "./ChartTypeSelect";

export default function HPChart({ parameter } : { parameter: Parameter }) {
    const [chartType, setChartType] = useState("line");

    return <div className="d-flex flex-column mb-5">
        <div className="d-flex flex-row justify-content-end">
            <ChartTypeSelect defaultValue={chartType} setChartType={setChartType}/>
        </div>
        {chartType == "line" ?
            <LineChart parameter={parameter}/>
        : chartType == "bar" ?
            <BarChart parameter={parameter}/>
        : null}
    </div>
}