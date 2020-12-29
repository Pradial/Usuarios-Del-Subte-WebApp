var ctx = document.getElementById('graficoC').getContext('2d');
var chart = new Chart(ctx, {
    type: 'line',

  
    data: {
        labels: labelArrayC,
        datasets: [{
            label: 'Cantidad de pasajeros',
            borderColor: 'rgb(61, 61, 233)',
            data: datosArrayC,
            pointRadius: 1,
            pointHoverRadius: 6,
            fill:false
        }]
    },    

    options: {
        legend:{
            display: false,
        },
        responsive: true,
        maintainAspectRatio: true,
        scales: {
            yAxes: [{
                
                ticks: {
                    beginAtZero:true,
                    fontSize: 13,
                    callback: function(value) {
                        value = value.toString();
                        value = value.split(/(?=(?:...)*$)/);
                        value = value.join('.');
                        return value;
                    },
                    fontStyle: 'bold',
                },
                
                
            }],
            xAxes: [{
                type: 'timecenter',
                time: {
                    unit: 'month'
                    },
                gridLines: {
                    offsetGridLines: true
                },
                ticks: {
                    callback: function(value) { 
                        var mes = moment(value.toString(), "MMM YYYY", "en", true);
                        mes.locale("es");
                        return mes.format("MMMM");
                    },
                    fontSize: 12,
                    fontStyle: 'bold',
                },                    
            }],
        },
        tooltips: {
                custom: function(tooltip) {
		            if (!tooltip) return;
		            tooltip.displayColors = false;
		        },
                callbacks : { 
                    title : function() {
                        return;
                    },
                    beforeLabel : function(tooltipItem, data) {
                        var dia = new Date(tooltipItem.xLabel).toLocaleDateString('es-Es', {weekday: 'long', day: 'numeric', month: 'long'})
                        return "Día: " + dia;
                    },
                    label : function(tooltipItem, data) {
                        var value = tooltipItem.yLabel;
                        value = value.toString();
                        value = value.split(/(?=(?:...)*$)/);
                        value = value.join('.');
                        return "Cantidad de Pasajeros: " + value;
                    },
            },
        },
        title: {
            display: true,
            text: 'Cantidad de Pasajeros Línea C',
            fontSize: 36,
            fontFamily: 'Helvetica Neue',
            fontColor: 'black',
        }
    }
});
