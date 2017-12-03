$(document).ready(function(){
    
    $("select[name=enfermedad]").change(function(){
        var id = $('select[name=enfermedad]').val();
        if(id!=0){
            var dataArray=["",""];
            var url = "Data?id="+id;
            $.ajax({
            url:url,
            success: function(data){
                    dataArray = data.split("-");
                    $("input[name=doctor]").val(dataArray[0]);
                    $("textarea[id=tratamiento]").val(dataArray[1]);
                }
            }); 
        }else{
            $("input[name=tecnico]").val("");
            $("textarea[id=tratamiento]").val("");
        }
    });
    
    $("button[name=insertar]").click(function(){
        var oficina = $('select[name=oficina]').val();
        var enfermedad = $('select[name=enfermedad]').val();
        var usuario = $('input[name=usuario]').val();
        if(oficina!=0 && enfermedad!=0 && usuario!=""){
            var fechaRevision = $('input[name=fechaRevision]').val();
            var fechaTermino = $('input[name=fechaTermino]').val();
            var url = "CRUD?idEnfermedad="+enfermedad
                        +"&idOficina="+oficina
                        +"&usuario="+usuario
                        +"&fechaRevision="+fechaRevision
                        +"&fechaTermino="+fechaTermino;
            $.ajax({
                url:url,
                type:'PUT',
                success:function(){
                    alert("Datos insertados correctamente");
                }
            });
        }else{
            alert("Rellenar todos los campos");
        }
        
    });
    
    $("button[name=modificar]").click(function(){
        var id=$('input[name=id]').val();
        var oficina = $('select[name=oficina]').val();
        var enfermedad = $('select[name=enfermedad]').val();
        var doctor = $('input[name=doctor]');
        var usuario = $('input[name=usuario]').val();
        var tratamiento = $('textbox[name=tratamiento]').val();
        if(oficina!=0 && enfermedad!=0 && usuario!="" && id!=""){
            var fechaRevision = $('input[name=fechaRevision]').val();
            var fechaTermino = $('input[name=fechaTermino]').val();
            var url = "CRUD?id="+id
                        +"&idEnfermedad="+enfermedad
                        +"&idOficina="+oficina
                        +"&usuario="+usuario
                        +"&doctor="+doctor
                        +"&tratamiento="+tratamiento
                        +"&fechaRevision="+fechaRevision
                        +"&fechaTermino="+fechaTermino;
            $.ajax({
                url:url,
                type:'POST',
                success:function(){
                    alert("Datos modificados correctamente");
                }
            });
        }else{
            alert("Rellenar todos los campos");
        }
    });
    
    $("button[name=eliminar]").click(function(){
        var id=$('input[name=id]').val();
        if(id!=0){
            var url="CRUD?idIncidencia="+id;
            $.ajax({
               url:url,
               type:'DELETE',
               success:function(){
                   alert("Incidencia eliminada correctamente");
               }
            });
        }else{
            alert("Rellene el nmr de la incidencia a eliminar");
        }
    });
    
    $("button[name=reporte]").click(function(){
        var url = "CRUD";
        $.ajax({
           url: url,
           type: "GET",
           success:function(data){
               var dataArray = data.split("?");
               var result="";
               var id="";
               var urlArray = window.location.toString().split("?");
               var urlArray2 = urlArray[0].split("index");
               var urlArray3 = urlArray2[0].split("CRUD");
               var url = urlArray3[0]+"CRUD";
               for(var i=0; i<dataArray.length-1;i++){
                   id = dataArray[i];
                   result+="<tr><td name='id'>"+dataArray[i]+
                           "</td><td>"+dataArray[++i]+
                           "</td><td>"+dataArray[++i]+
                           "</td><td>"+dataArray[++i]+
                           "</td><td>"+dataArray[++i]+
                           "</td><td>"+dataArray[++i]+
                           "</td><td>"+dataArray[++i]+
                           "</td><td><a href='"+url+"?id="+id+
                           "'><img class='img-seleccion' src=img/check.png"+
                           " /></a>"
                           "</td></tr>";
               }
               //alert(result);
               $("tbody[name=reporte]").html(result);
           }
        });
    });
    
    
});