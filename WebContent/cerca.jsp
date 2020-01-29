<html>
<head><title>Utilizzo del Bean</title></head>
<body>
    <jsp:useBean id="diz" scope="session" class="app.AccessBean"/>
    <jsp:setProperty name="diz" property="parola" param="parola"/>
    la parola:
    <jsp:getProperty name="diz" property="parola"/>
    significa: 
    <jsp:getProperty name="diz" property="significato"/>
    </br>
    <a href="\WebContent">back</a>
</body>
</html>