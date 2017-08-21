<!doctype html>
<html ng-app="myApp">
<head>
<script data-require="angular.js@1.1.x" src="http://code.angularjs.org/1.1.5/angular.min.js" data-semver="1.1.5"></script>
<link data-require="bootstrap-css@2.3.2" data-semver="2.3.2" rel="stylesheet" href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" />
<script src="js/main.js"></script>
<link rel="stylesheet" href="css/main.css" />
<script src="js/multiselect.js"></script>

</head>
<body>
    <div ng-controller="myCtrl">
        <form name="clusterchangeform">
            <br><br>
            Trial ID: <multiselect class="input-xlarge" multiple="true" ng-model="studyid" options="c.studyid for c in trialid" change="selected()" ></multiselect>
            <br><br>
            Cluster Name: <select ng-model="clustername" ng-options="x for x in cname" required >
			</select>
			<br><br><br>
            <button type="submit" ng-click="sendPost()">Send</button>
            <br><br>
        </form>
    </div>
</body>
</html>