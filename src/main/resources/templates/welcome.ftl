<#import "/spring.ftl" as spring/>

<!doctype html>
<html>
    <head>
        <title>Welcome</title>
        <link href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" rel="stylesheet"/>
    </head>

    <body>
        <div class="container">
            <#if name??>
                <form id="logout" method="post" action="/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
                <h3>
                    Welcome ${name} | <a class="btn btn-link" onclick="document.forms['logout'].submit()">Logout</a>
                </h3>
                <p><a class="btn btn-link" href="/web">AJAX</a></p>
                <p><a class="btn btn-link" href="/web/product">product</a></p>
                <p><a class="btn btn-link" href="/web/error">ERROR</a></p>
            </#if>
        </div>
    </body>

    <script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</html>
