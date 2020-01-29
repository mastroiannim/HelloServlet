<html>
<head><title>Utilizzo del Bean</title></head>
<body>
    <jsp:useBean id="diz" scope="session" class="app.AccessBean"/>
    <form action="cerca.jsp">
     <jsp:getProperty name="diz" property="parole"/>
     <input type="submit" value="Submit">
    </form>
    <a href="\WebContent\aggiungi.jsp">add</a>
</body>
</html>