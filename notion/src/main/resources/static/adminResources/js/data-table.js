$(function(){"use strict";$("#example1").DataTable(),$("#example2").DataTable({paging:!0,lengthChange:!1,searching:!1,ordering:!0,info:!0,autoWidth:!1}),$("#example").DataTable({dom:"Bfrtip",buttons:["copy","csv","excel","pdf","print"]}),$("#tickets").DataTable({paging:!0,lengthChange:!0,searching:!0,ordering:!0,info:!0,autoWidth:!1}),$("#productorder").DataTable({paging:!0,lengthChange:!0,searching:!0,ordering:!0,info:!0,autoWidth:!1}),$("#complex_header").DataTable(),$("#example5 tfoot th").each(function(){var e=$(this).text();$(this).html('<input type="text" placeholder="Search '+e+'" />')}),(e=$("#example5").DataTable()).columns().every(function(){var e=this;$("input",this.footer()).on("keyup change",function(){e.search()!==this.value&&e.search(this.value).draw()})});var e=$("#example6").DataTable();$("button").click(function(){var a=e.$("input, select").serialize();return alert("The following data would have been submitted to the server: \n\n"+a.substr(0,120)+"..."),!1})});