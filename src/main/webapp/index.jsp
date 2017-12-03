<%@page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema Incidencias</title>
        <link type="text/css" rel="stylesheet" href="css/stylesheet.css"/>
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
    </head>
    <body>
        <header>
            <h1>Sistema Incidencias</h1>
        </header>
        <div id="container">
            <div class="input-div">
                <span>NMR: </span>
                <input name="id" class="" 
                       value='${incidencia.id}'
                       
                       type="number "/>
            </div>
            <div class="input-div">
                <span>OFICINA: </span>
                <select name="oficina" required>
                    <option value=0>----</option>
                    <c:forEach var="oficina" items="${oficinas}">
                        <option value="${oficina.id}"
                            <c:if test="${oficina.oficina==incidencia.oficina}">
                                selected
                            </c:if>
                        >${oficina.oficina}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-div">
                <span>ENFERMEDAD: </span>
                <select id="enfermedad" name="enfermedad"
                        value="${incidencia.enfermedad}"
                        required>
                    <option value=0>----</option>
                    <c:forEach var="enfermedad" items="${enfermedades}">
                        <option value="${enfermedad.id}" 
                                <c:if test="${enfermedad.enfermedad==incidencia.enfermedad}">
                                    selected
                                </c:if>
                        >${enfermedad.enfermedad}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-div">
                <span>FECHA REVISION: </span>
                <input name="fechaRevision" 
                       value="<fmt:formatDate value="${fechaRevision}" pattern="yyyy-MM-dd" type="date"/>"
                       
                        type="date" readonly/>
            </div>
            <div class="input-div">
                <span>USUARIO: </span>
                <input name="usuario" type="text"
                           value="${incidencia.usuario}"/>
            </div>
            <div class="input-div">
                <span>DOCTOR: </span>
                <input id=""doctor name="doctor" value="${incidencia.doctor}" type="text" readonly/>
            </div>
            <div class="div-tratamiento">
                <span>TRATAMIENTO:</span>
                <textarea id="tratamiento" readonly>${incidencia.solucion}</textarea>
            </div>
            <div class="input-div">
                <span>FECHA TERMINO: </span>
                <input name="fechaTermino" value="<fmt:formatDate value="${fechaTermino}" pattern="yyyy-MM-dd" type="date"/>"
                       
                       
                       type="date" readonly>
            </div>
            <div id="div-reporte">
                <table id="reporte">
                    <thead>
                    <th colspan="8">REPORTE</th>
                        <tr>
                            <td>id</td>
                            <td>usuario</td>
                            <td>oficina</td>
                            <td>enfermedad</td>
                            <td>Fecha Revision</td>
                            <td>Fecha Termino</td>
                            <td>Tratamiento</td>
                            <td>Select</td>
                        </tr>
                    </thead>
                    <tbody name="reporte">
                    </tbody>
                </table>
            </div>
            <div id="div-buttons">
                <button name="insertar">INSERTAR</button>
                <button name="modificar">MODIFICAR</button>
                <button name="eliminar">ELIMINAR</button>
                <button name="reporte">REPORTE</button>
            </div>
        </div>
    </body>
</html>
