<!doctype html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Galahad - ${meta(name: 'app.name')}</title>
	</head>

	<body>
        <div class="billboard">
            <r:img dir="images" file="billboard.jpg" />
            <div class="billboard-caption">
              <h1>Welcome to OrganizeVC.</h1>
              <p class="lead">OrganizeVC is a space reservation system that allows you to book various rooms on Vassar’s campus for events.</p>
              <g:link class="btn btn-lg btn-primary" controller="registration">Sign up today</g:link>
            </div>
        </div>
		
        <div class="row featurette">
            <div class="col-md-7">
				<h2>Now you can now ensure your org, study session, or impromptu drum circle has the appropriate space!<span class="text-muted"></span></h2>
				<p class="lead">Rather than hunting for a free space by blundering through Rocky interrupting lectures or walking in on film screenings, OrganizeVC allows you to reserve a room in advance or even on the day of. You can reserve rooms for several hours at time, and also view a reservation history for any given date.</p>
            </div>
            <div class="col-md-5">
                <img class="featurette-image img-responsive" src="/images/vassar.png" width="500" style="border-radius: 10px;" alt="Generic placeholder image">
			</div>
        </div>
		
	</body>
</html>
