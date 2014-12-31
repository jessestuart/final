<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Sign Up</title>
	</head>

	<body>
	    <div class="row">
	        <div class="col-md-6 col-md-offset-3">
        	    <div class="page-header">
            	    <h1>Sign Up</h1>
        	    </div>
        	    
        	    <g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<g:hasErrors bean="${userInstance}">
				<bootstrap:alert class="alert-danger">
				<ul>
					<g:eachError bean="${userInstance}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
				</bootstrap:alert>
				</g:hasErrors>

				<fieldset>
					<g:form class="form-horizontal" action="save">
						<fieldset>
					        <f:with bean="${userInstance}">
                                <f:field property="fullName" />
                                <f:field property="email" />
                                <f:field property="password" />
                            </f:with>
                            
							<div class="form-group">
								<div class="col-sm-5 col-sm-offset-4">
									<button type="submit" class="btn btn-primary">Sign Up</button>
								</div>
							</div>
						</fieldset>
					</g:form>
				</fieldset>
				
    	    </div>
	    </div>
	</body>
</html>
