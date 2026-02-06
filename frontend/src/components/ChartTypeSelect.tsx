import "bootstrap/js/dist/dropdown";
import type { ChangeEvent } from "react";

type Props = {
    defaultValue: string,
    setChartType: (chartType: string) => void
};

export default function ChartTypeSelect({ defaultValue, setChartType } : Props) {
    const handleSelect = (event: ChangeEvent<HTMLSelectElement>) => {
        setChartType(event.target.value);
    };

    return <div className="col-4 col-lg-2">
        <select className="form-select" aria-label="Select chart type" onChange={handleSelect} defaultValue={defaultValue}>
            <option value="line">Line chart</option>
            <option value="bar">Bar chart</option>
        </select>
    </div>
}