<html>
<head><title>default.jsp</title></head>
<body>
    <jsp:useBean id="diz" scope="session" class="app.AccessBean"/>
    <form action="cerca.jsp">
        <select name="parola">
        <%
            app.AccessBean ilDizionario = (app.AccessBean) pageContext.getAttribute("diz");
            String[] parole = diz.getElenco();
            StringBuilder ulList = new StringBuilder();
            for (int i=0; i < parole.length; i++){
                ulList.append("<option value=\"");
                ulList.append(parole[i]);
                ulList.append("\">");
                ulList.append(parole[i]);
                ulList.append("</option>");
            }
            out.println(ulList.toString());
        %>
        </select>
        <input type="submit" value="Submit">
    </form>
    <a href="\WebContent\aggiungi.jsp">add</a>
    </br>
    <jsp:getProperty name="diz" property="dbFilePath"/>
</body>
</html>