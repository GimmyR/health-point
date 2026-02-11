import type Parameter from "../interfaces/Parameter";
import { Chart as ChartJS, CategoryScale, LinearScale, BarElement, Tooltip, Legend, type ChartOptions } from "chart.js";
import { Bar } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Tooltip,
  Legend
);

export default function BarChart({ parameter } : { parameter: Parameter }) {
    const data = {
        labels: parameter.entries.map(entry => entry.entryDate),
        datasets: [{
            label: parameter.name,
            data: parameter.entries.map(entry => entry.value),
            backgroundColor: "#5184FF",
            pointRadius: 10,
            pointHoverRadius: 15
        }]
    };

    const options: ChartOptions<"bar"> = {
        scales: { 
            x: {
                ticks: {
                    callback: (value) => {
                        const label = new Date(parameter.entries[value as number].entryDate).toLocaleString();
                        const [date, time] = label.split(" ");
                        return [date, time];
                    }
                } 
            },
            y: {
                min: parameter.min,
                max: parameter.max
            }
        }
    };

    return <Bar data={data} options={options}/>
}