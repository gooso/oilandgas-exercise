
      // Code goes here

$(function () {
    $.getJSON('daily.json', function (data) {
        Highcharts.chart('container', {
            chart: {
                zoomType: 'x'
            },
            title: {
                text: 'Daily Extractions'
            },
            subtitle: {
                text: document.ontouchstart === undefined ?
                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'
            },
            xAxis: {
                type: 'datetime'
            },
            yAxis: {
                title: {
                    text: 'Extracted Amount'
                }


            },
            legend: {
                enabled: false
            },
            plotOptions: {
                area: {
                    fillColor: {
                        linearGradient: {
                            x1: 0,
                            y1: 0,
                            x2: 0,
                            y2: 1
                        },
                        stops: [
                            [0, Highcharts.getOptions().colors[0]],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        radius: 2
                    },
                    lineWidth: 1,
                    states: {
                        hover: {
                            lineWidth: 1
                        }
                    },
                    threshold: null
                }
            },
            series: [{
                type: 'bar',
                regression: false,
                name: 'Extracted',
                data: data
            }]
        });



    });


    $.getJSON('forecast.json', function (data) {
        var d = new Date(data.regressionpoints[0][0]);

        Highcharts.chart('container2', {
            xAxis: {
                               type: 'datetime',
                               //max: data.data[data.data.length-1][0]
                               max: data.regressionpoints[0][0]


            },
            yAxis: {
                title: {
                    text: ''
                }            },
            title: {
                text: 'Extraction Forecast',
            },
            subtitle: {
                text:  'Estimated Completion Date: '+d,
                style: {  color: '#FF0000',
                          fontWeight: 'bold'}
            },
            series: [{
                type: 'line',
                name: 'Regression Line',
                data: data.regressionpoints,
                marker: {
                    enabled: false
                },
                states: {
                    hover: {
                        lineWidth: 0
                    }
                },
                enableMouseTracking: false
            }, {
                type: 'scatter',
                name: 'Observations',
                regression:true,
                data: data.data,
                marker: {
                    radius: 4
                }
            }]
        });




      data.data.splice(data.data.length-1,1);



      Highcharts.chart('container3', {
                  xAxis: {
                                     type: 'datetime',
                                     max: data.data[data.data.length-1][0]
                                     //max: data.regressionpoints[0][0]


                  },
                  yAxis: {
                      title: {
                          text: 'Current Reserves'
                      }            },
                  title: {
                      text: 'Daily Extraction Trend'
                  },
                  series: [{
                      type: 'line',
                      name: 'Regression Line',
                      data: data.regressionpoints,
                      marker: {
                          enabled: false
                      },
                      states: {
                          hover: {
                              lineWidth: 0
                          }
                      },
                      enableMouseTracking: false
                  }, {
                      type: 'scatter',
                      name: 'Observations',
                      regression:true,
                      data: data.data,
                      marker: {
                          radius: 4
                      }
                  }]
              });

            });

});
