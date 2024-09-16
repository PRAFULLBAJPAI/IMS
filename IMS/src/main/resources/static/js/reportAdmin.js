 /*------------------FILTER DATA OF DATATABLE FOR TRAINING NAME DROPDOWN: SUYASH-------------------*/
/******* @Author SWATI *******/

    $(document).ready(function () {

        function filterTableByEmployeeName(value) {
            $('#example').DataTable().column(1).search(value).draw();
        }

        function filterTableByTrainingName(value) {
            $('#example').DataTable().column(0).search(value).draw();
        }

        $('#employeeNameFilter').on('change', function () {
            var selectedValue = $(this).val();
            if (selectedValue === '') {
                $('#example').DataTable().search('').draw();
            } else {
                filterTableByEmployeeName(selectedValue);
            }
        });

        $('#trainingNameFilter').on('change', function () {
            var selectedValue = $(this).val();
            if (selectedValue === '') {
                $('#example').DataTable().search('').draw();
            } else {
                filterTableByTrainingName(selectedValue);
            }
        });
    });

/*-----------------------------------------REPORT 2: TABLE JS-------------------------------------*/
  
  $('table.display').DataTable({
        'responsive': true,
        "pageLength": 5,
          
    });

/*-------------------------REPORT 2: OVERDUE AND PENDING JS FOR DROPDOWN---------------------------*/


    $(document).ready(function(){
        $('#trainingNamePending').on('change', function(){
            var selectedTraining = $(this).val().trim();
            
            $('#pendingStatusTable tbody tr').hide();
            
            if(selectedTraining === '') {
                $('#pendingStatusTable tbody tr').show();
            } else {
                $('#pendingStatusTable tbody tr').each(function(){
                    var training = $(this).find('td:nth-child(2)').text().trim();
                    if(training === selectedTraining) {
                        $(this).show();
                    }
                });
            }
        });
    });

$(document).ready(function() {
    $('#employeeNamePending').change(function() {
        var selectedEmployee = $(this).val();
        $('#pendingStatusTable tbody tr').hide();

        if (selectedEmployee !== '') {
            $('#pendingStatusTable tbody tr').each(function() {
                if ($(this).find('td:first').text() === selectedEmployee) {
                    $(this).show();
                }
            });
        } else {
            $('#pendingStatusTable tbody tr').show();
        }
    });

    $('#pendingStatusTable tbody').on('click', 'tr', function() {
       
        var employeeName = $(this).find('td:first').text();
        var trainingName = $(this).find('td:nth-child(2)').text();

        
    });
});

/*************** TOGGLE BETWEEN TRAINING NAME DROPDOWN AND EMPLOYEE NAME DROPDOWN **************/

document.addEventListener('DOMContentLoaded', function () {
        var trainingNameFilter = document.getElementById('trainingNameFilter');
        var employeeNameFilter = document.getElementById('employeeNameFilter');

        trainingNameFilter.disabled = false;
        employeeNameFilter.disabled = false;

        trainingNameFilter.addEventListener('change', function () {

            employeeNameFilter.disabled = (trainingNameFilter.value !== "#");

            if (trainingNameFilter.value === "#") {
                employeeNameFilter.value = "#";
            }
        });

        employeeNameFilter.addEventListener('change', function () {
            trainingNameFilter.disabled = (employeeNameFilter.value !== "#");

            if (employeeNameFilter.value === "#") {
                trainingNameFilter.value = "#";
            }
        });
    });

    function fetchReport() {
        console.log("Fetching report...");
    }


/*************** TOGGLE BETWEEN PENDING TRAINING NAME DROPDOWN AND PENDING EMPLOYEE NAME DROPDOWN **************/

document.addEventListener('DOMContentLoaded', function () {
    var trainingNameFilter = document.getElementById('trainingNamePending');
    var employeeNameFilter = document.getElementById('employeeNamePending');

    trainingNameFilter.disabled = false;
    employeeNameFilter.disabled = false;

    trainingNameFilter.addEventListener('change', function () {
        employeeNameFilter.disabled = (trainingNameFilter.value !== "");

        if (trainingNameFilter.value === "") {
            employeeNameFilter.value = "";
        }
    });

    employeeNameFilter.addEventListener('change', function () {
        trainingNameFilter.disabled = (employeeNameFilter.value !== "");

        if (employeeNameFilter.value === "") {
            trainingNameFilter.value = "";
        }
    });
});

function fetchReport() {
    console.log("Fetching report...");
}


/************** ALL TRAININGS AND THEIR EMPLOYEE NAME AND STATUS ********************/
$(document).ready(function () {
    function fetchAllTrainings() {
            $.ajax({
                url: 'getAllTrainingsWithTheirEmpNameAndStatus',
                type: 'GET',
                dataType: 'json',
                success: function (response) {
                    try {
                        var data = response.payload;
                        var tableBody = $('#example tbody');
                        tableBody.empty();

                        $.each(data, function (index, row) {
                            var newRow = $('<tr>');

                            $.each(row, function (i, entry) {
                                var newCell = $('<td>');

                                if (i === 2) {
                                    entry = (entry === 'P') ? 'Pending' : (entry === 'C') ? 'Completed' : entry;
                                }

                                newCell.text(entry);
                                newRow.append(newCell);
                            });

                            tableBody.append(newRow);
                        });
                    } catch (error) {
                        if ($('#trainingNameFilter').val() !== '#') {
                            console.error(error);
                            Swal.fire({
                                icon: 'error',
                                title: 'Error',
                                text: 'An error occurred while processing the data.',
                            });
                        }
                    }
                },
                error: function (xhr, status, error) {
                    if ($('#trainingNameFilter').val() !== '#') {
                        console.error(xhr.responseText);
                        Swal.fire({
                            icon: 'error',
                            title: 'Error',
                            text: 'An error occurred while making the request.',
                        });
                    }
                }
            });
        }

    fetchAllTrainings();

    $('#trainingNameFilter').on('change', function () {
        var selectedTraining = $(this).val();

        if (selectedTraining === '#') {
            fetchAllTrainings();
        } else {
        }
    });

    $('#employeeNameFilter').on('change', function () {
        var selectedEmployee = $(this).val();

        if (selectedEmployee === '#') {
            fetchAllTrainings();
        } else {
        }
    });
    $('#trainingNameFilter').on('change', function () {
            var selectedTraining = $(this).val();

            if (selectedTraining === '#') {
                fetchAllTrainings();
            } else {
            }
        });
});

/************** EMPLOYEE NAME AND STATUS BY TRAINING NAME ********************/

function fetchReport() {
    var trainingName = $("#trainingNameFilter").val();

    $.ajax({
        type: "GET",
        url: "getEmployeeNameAndStatusByTrainingName",
        data: { trainingName: trainingName },
        dataType: "json",
        success: function (response) {
            try {
                updateTable(response.payload);
            } catch (error) {
                handleError(error);
            }
        },
        error: function (error) {
            handleError(error);
        }
    });
}

function updateTable(data) {
    var tableBody = $("#example tbody");
    tableBody.empty();

    for (var i = 0; i < data.length; i++) {
        var row = "<tr>";
        row += "<td>" + data[i][0] + "</td>";
        row += "<td>" + data[i][1] + "</td>";

        // Check the status and print "Pending" or "Completed"
        var status = data[i][2];
        if (status === "P") {
            row += "<td>Pending</td>";
        } else if (status === "C") {
            row += "<td>Completed</td>";
        } else {
            // Handle other status values if needed
            row += "<td>" + status + "</td>";
        }

        row += "</tr>";

        tableBody.append(row);
    }
}


function handleError(error) {
    console.error("Error fetching data: " + error);
}


/************** TRAINING NAME AND STATUS BY EMPLOYEE NAME ********************/

function fetchReportOfEmployee() {
    var empName = $("#employeeNameFilter").val();

    $.ajax({
        type: "GET",
        url: "getTrainingNameAndStatusByEmployeeName",
        data: { empName: empName },
        dataType: "json",
        success: function (response) {
            try {
                updateTable(response.payload);
            } catch (error) {
                handleError(error);
            }
        },
        error: function (error) {
            handleError(error);
        }
    });
}


