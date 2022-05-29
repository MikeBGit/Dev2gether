const niceChartData = decodeHtml(chartData);
const chartJsonArray = JSON.parse(niceChartData);

const arrayLength = chartJsonArray.length;
const numericData = [];
const labelData = [];

for(let i=0; i< arrayLength; i++){
    numericData[i] = chartJsonArray[i].value;
    labelData[i] = chartJsonArray[i].label;
}

const ctx = document.getElementById('myChart').getContext('2d');
new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels: labelData,
        datasets: [{
            label: 'Statuses',
            data: numericData,
            backgroundColor: [
                'rgb(168,118,99)',
                'rgb(27,227,60)',
                'rgb(250,221,141)'
            ],
            hoverOffset: 25
        }]
    },
    // options: {
    //     title : {
    //         display: false,
    //         text: 'Projects Statuses'
    //     }
    // }
});

//THIS TRANSFORMS the cryptic to the usable
function decodeHtml(html){
    const txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}