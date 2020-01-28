<html>
<head><title>Utilizzo del Bean</title></head>
<body>
<jsp:useBean id="diz" scope="session" class="app.AccessBean"/>
<jsp:setProperty name="diz" property="parola" value="cane"/>
<jsp:getProperty name="diz" property="significato"/>
<jsp:getProperty name="diz" property="parole"/>
</body>
</html>