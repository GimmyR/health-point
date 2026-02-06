import type Parameter from "../interfaces/Parameter";
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Tooltip, Legend, type ChartOptions } from "chart.js";
import { Line } from "react-chartjs-2";

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Tooltip,
  Legend
);

export default function LineChart({ parameter } : { parameter: Parameter }) {
    const data = {
        labels: parameter.entries.map(entry => entry.dateTime),
        datasets: [{
            label: parameter.name,
            data: parameter.entries.map(entry => entry.value),
            fill: false,
            borderColor: '#FF5858',
            tension: 0.1,
            pointRadius: 10,
            pointHoverRadius: 15
        }]
    };

    const options: ChartOptions<"line"> = {
        scales: { 
            x: {
                ticks: {
                    maxRotation: 45,
                    minRotation: 45,
                    callback: (value) => {
                        const label = parameter.entries[value as number].dateTime;
                        const [date, time] = label.split(" ");
                        return [date, time];
                    }
                } 
            },
            y: {
                min: 35,
                max: 40
            }
        }
    };

    return <Line data={data} options={options}/>
}