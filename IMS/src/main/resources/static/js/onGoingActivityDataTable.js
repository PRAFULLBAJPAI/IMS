$(document).ready(function (){
    var table = $('#example').DataTable({
        'responsive': true,
          paging: false,
        scrollCollapse: true,
         scrollY: '40vh',
        dom: 'Bfrtip'
    });

   
    $('#btn-show-all-children').on('click', function(){
      
        table.rows(':not(.parent)').nodes().to$().find('td:first-child').trigger('click');
    });

    $('#btn-hide-all-children').on('click', function(){
        
        table.rows('.parent').nodes().to$().find('td:first-child').trigger('click');
    });
});