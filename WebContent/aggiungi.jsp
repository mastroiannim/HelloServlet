<html>
<head><title>Utilizzo del Bean</title></head>
<body>
    <jsp:useBean id="diz" scope="session" class="app.AccessBean"/>

    <form action="add" method="post">
        parola:<br>
        <input type="text" name="parola"><br>
        significato:<br>
        <input type="text" name="significato">
        <input type="submit" value="Submit">
    </form>
    <a href="\WebContent">back</a>
</body>
</html>