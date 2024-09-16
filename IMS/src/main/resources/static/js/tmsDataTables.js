$(document).ready(function() {
    $('#example').DataTable( {
		responsive: true,
		   paging: false,
        scrollCollapse: true,
         scrollY: '41vh',
        dom: 'Bfrtip',
        buttons: [
            {
                extend:    'copyHtml5',
                text:      '<i class="fa-solid fa-copy" style="color: #B197FC; font-size:18px;"></i>',
                titleAttr: 'Copy'
            },
            {
                extend:    'excelHtml5',
                text:      '<i class="fa fa-file-excel-o"></i>',
                titleAttr: 'Excel'
            },
            {
                extend:    'csvHtml5',
                text:      '<i class="fa-solid fa-file-csv " style="color: #1c76ba; font-size:18px;"></i>',
                titleAttr: 'CSV'
            },
            {
                extend:    'pdfHtml5',
                text:      '<i class="fa-solid fa-file-pdf" style="color: #74C0FC; font-size:18px;"></i>',
                titleAttr: 'PDF'
            },
            {
                extend:    'print',
                text:      '<i class="fa-solid fa-print" style="color: #6595ff; font-size:18px;"></i>',
                titleAttr: 'PRINT'
            }
        ]
   
    } );
} );