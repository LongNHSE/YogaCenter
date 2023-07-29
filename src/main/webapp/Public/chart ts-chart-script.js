
var TUTORIAL_SAVVY = {
    loadData: function () {
        var formattedListArray = [];
        $.ajax({
            async: false,
            url: "http://localhost:8080/YogaCenter/DashBoardController",
            dataType: "json",
            success: function (JsonData) {
                // Loop through the received JSON data and convert it to an array of objects
                for (var type in JsonData) {
                    if (JsonData.hasOwnProperty(type)) {
                        var traineeCount = JsonData[type];
                        formattedListArray.push({
                            xValue: type,
                            yValue: traineeCount
                        });
                    }
                }
                for (var i = 0; i < formattedListArray.length; i++) {
                    var dataEntry = formattedListArray[i];
                    console.log("Type: " + dataEntry.xValue + ", Trainee Count: " + dataEntry.yValue);
                }

                // Print the data using console.log()

            }
        });
        return formattedListArray;
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
                    text: "World Wide Wine Production 2018"
                }
            }
        });
    }

};
$(document).ready(function () {

    var data = TUTORIAL_SAVVY.loadData();
    TUTORIAL_SAVVY.testData(data);
});