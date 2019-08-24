<#import "/spring.ftl" as spring/>

<!doctype html>
<html>
    <head>
        <title>Create an account</title>
        <link href="/webjars/bootstrap/4.1.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
        <link href="/css/index.css" type="text/css" rel="stylesheet"/>
    </head>

    <body>
        <@spring.bind "user"/>
        <div class="container">
            <form method="post" class="">
                <h2 class="">Create your account</h2>
                <div class="form-group">
                    <#if username??>
                        <#assign uClaz="class='form-control is-invalid'"/>
                    <#else>
                        <#assign uClaz="class='form-control'"/>
                    </#if>
                    <@spring.formInput "user.username" uClaz/>
                    <div class="invalid-feedback">
                        <@spring.showErrors ""/>
                    </div>
                </div>

                <div class="form-group">
                    <#if password??>
                        <#assign pClaz="class='form-control is-invalid'"/>
                    <#else>
                        <#assign pClaz="class='form-control'"/>
                    </#if>
                    <@spring.formPasswordInput "user.password" pClaz/>
                    <div class="invalid-feedback">
                        <@spring.showErrors ""/>
                    </div>
                </div>

                <div class="form-group">
                    <#if passwordConfirm??>
                        <#assign pcClaz="class='form-control is-invalid'"/>
                    <#else>
                        <#assign pcClaz="class='form-control'"/>
                    </#if>
                    <@spring.formPasswordInput "user.passwordConfirm" pcClaz/>
                    <div class="invalid-feedback">
                        <@spring.showErrors ""/>
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button class="btn btn-lg btn-info btn-block" type="submit">Submit</button>

            </form>
        </div>
    </body>

    <script type="text/javascript" src="/webjars/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</html>