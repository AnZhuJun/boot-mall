<!doctype html>
<html>
<head>
    <title>Index page</title>
    <link href="/css/index.css" rel="stylesheet">
    <script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
    <h1 id="title">${title}</h1>
    <p></p>
    <p></p>
    <p></p>
    <p></p>
    <p></p>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</body>

<script>
    $(function(){
       $("#title").click(function(){
           var token = $("input[name='_csrf']").attr("value");
           $.ajax({url:"http://localhost:8081/api/hello",
                   headers:{"x-csrf-token":token},
                   type:"POST",
                   data:{name:"azj"},
                   success:function (data,status) {
                     console.log(status);
                     $('p').html(data.action + " " + data.name);
           }
           });
       });
    });
</script>
</html>
