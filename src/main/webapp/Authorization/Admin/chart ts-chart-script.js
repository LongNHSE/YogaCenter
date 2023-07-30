




var TUTORIAL_SAVVY = {
    loadData: function () {
        var formattedListArray = [];

        $.ajax({
            async: false,
            url: "http://localhost:8080/YogaCenter/DashBoardController",
            dataType: "json",
            success: function (JsonData) {
                console.log(JsonData);
                var traineeData = JsonData.traineeData;
                // Loop through the received JSON data and convert it to an array of objects
                for (var type in traineeData) {
                    if (traineeData.hasOwnProperty(type)) {
                        var traineeCount = traineeData[type];
                        formattedListArray.push({
                            xValue: type,
                            yValue: traineeCount
                        });
                    }
                }



            }
        });
        return formattedListArray;
    },
    loadRevenueData: function () {
        var formattedRevenueListArray = [];

        $.ajax({
            async: false,
            url: "http://localhost:8080/YogaCenter/DashBoardController",
            dataType: "json",
            success: function (JsonData) {
                console.log(JsonData);
                var revenueData = JsonData.revenueData;
                console.log(revenueData);
                // Loop through the received JSON data and convert it to an array of objects
                for (var semester in revenueData) {
                    if (revenueData.hasOwnProperty(semester)) {
                        var revenue = revenueData[semester];
                        formattedRevenueListArray.push({
                            xValue: semester,
                            yValue: revenue
                        });
                    }
                }



            }
        });
        return formattedRevenueListArray;
    },

    loadDataReceipt: function () {
        var formattedListReceiptArray = [];
        $.ajax({
            async: false,
            url: "http://localhost:8080/YogaCenter/DashBoardController",
            dataType: "json",
            success: function (JsonData) {


                var receiptData = JsonData.receiptData;
                console.log(receiptData);
                for (var date in receiptData) {
                    if (receiptData.hasOwnProperty(date)) {
                        var receiptCount = receiptData[date];
                        var dateObj = new Date(date);
                        var formattedDate = formatDateToDdMmYyyy(dateObj);
                        formattedListReceiptArray.push({

                            xValue: dateObj,
                            xValueFormatted: formattedDate,
                            yValue: receiptCount
                        });
                    }
                }
                // Print the data using console.log()

            }
        });
        formattedListReceiptArray.sort(function (a, b) {
            return a.xValue - b.xValue;
        });
        console.log(formattedListReceiptArray);
        return formattedListReceiptArray;
    },
    /*Makes the AJAX calll (synchronous) to load a Student Data*/

    testData: function (data) {
        var xValues = [];
        for (var i = 0; i < data.length; i++) {
            var dataEntry = data[i];

            xValues.push(dataEntry.xValue);
        }
        var yValues = [];
        for (var i = 0; i < data.length; i++) {
            var dataEntry = data[i];
            yValues.push(dataEntry.yValue);
        }
        var barColors = [
            "#b91d47",
            "#00aba9",
            "#2b5797",
            "#e8c3b9",
            "#1e7145"
        ];
        new Chart("myChart", {
            type: "pie",
            data: {
                labels: xValues,
                datasets: [{
                        backgroundColor: barColors,
                        data: yValues
                    }]
            },
            options: {
                title: {
                    display: true,
                    text: "Number of trainees in class type"
                }
            }
        });
    },

    receiptData: function (data) {


        // Print the data using console.log()
        var xValues = [];
        for (var i = 0; i < data.length; i++) {
            var dataEntry = data[i];
            xValues.push(dataEntry.xValueFormatted);
        }
        var yValues = [];
        for (var i = 0; i < data.length; i++) {
            var dataEntry = data[i];
            yValues.push(dataEntry.yValue);
        }

        new Chart("receiptChart", {
            type: "line",
            data: {
                
                labels: xValues,
                datasets: [{
                        fill: false,
                        lineTension: 0,
                        backgroundColor: "rgba(0,0,255,1.0)",
                        borderColor: "rgba(0,0,255,0.1)",
                        data: yValues,

                    }]
            },

            options: {
                responsive: false,
                title: {
                    display: true,
                    text: "Number of trainees register"
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1 // Set the y-axis step size to 1
                        }
                    }
                }
            }

        });
    },
    revenueData: function (data) {


        // Print the data using console.log()
        var xValues = [];
        for (var i = 0; i < data.length; i++) {
            var dataEntry = data[i];
            xValues.push(dataEntry.xValue);
        }
        var yValues = [];
        for (var i = 0; i < data.length; i++) {
            var dataEntry = data[i];

            yValues.push(dataEntry.yValue);
        }

        new Chart("revenueChart", {
            type: "bar",
            data: {
                labels: xValues,
                datasets: [{
                        fill: false,
                        lineTension: 0,
                        backgroundColor: "rgba(0,0,255,1.0)",
                        borderColor: "rgba(0,0,255,0.1)",
                        data: yValues,

                    }]
            },

            options: {
                locale: 'en-US',
                title: {
                    display: true,
                    text: "Revenue in 2023"
                },
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            stepSize: 1 // Set the y-axis step size to 1
                        }
                    }
                }
            }

        });
    }








};

function formatDateToDdMmYyyy(dateObj) {
    var day = dateObj.getDate().toString().padStart(2, '0');
    var month = (dateObj.getMonth() + 1).toString().padStart(2, '0'); // Months are zero-based
    var year = dateObj.getFullYear().toString();

    return day + '-' + month + '-' + year;
}
$(document).ready(function () {

    var data = TUTORIAL_SAVVY.loadData();
    var receiptData = TUTORIAL_SAVVY.loadDataReceipt();
    var revenueData = TUTORIAL_SAVVY.loadRevenueData();
    TUTORIAL_SAVVY.testData(data);
    TUTORIAL_SAVVY.revenueData(revenueData);
    TUTORIAL_SAVVY.receiptData(receiptData);
});