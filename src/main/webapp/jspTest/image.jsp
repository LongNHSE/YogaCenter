


<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Display Images</title>
        <style>
            .imageStyle {
                max-width: 200px;
                max-height: 200px;
                margin-bottom: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Uploaded Images</h1>

        <%-- Iterate over the imageList received from the servlet --%>
        <%
            List<String> a = (List<String>) request.getAttribute("imageList");
            //        List<byte[]> imageList = (List<byte[]>) request.getAttribute("imageList");
            //        if (imageList != null && !imageList.isEmpty()) {
            for (String imageData : a) {
                // Convert the binary image data to Base64 string
                //                String base64Image = java.util.Base64.getEncoder().encodeToString(imageData);
%>
        <img class="imageStyle" src="data:image/jpeg;base64,<%= imageData%>" />
        <%
            }
            //        } else {
        %>
        <p>No images to display.</p>
        <%
//        }
           
        %>



    </body>
</html>

