def call(Map pipelineParams) {
	pipeline {
		agent{
			node {
				label pipelineParams.node ?: 'docker'
				}
			}
		tools {
			nodejs 'NodeJS-6.9.5'
		}

	environment {
		ENV = "${pipelineParams.env}"
		MODE = "${pipelineParams.mode ?: "BUILD" }"
		VERSION_TO_DEPLOY = "${}"
	}

	stages {
		stage('Setup Params') {}
	}
}

private void deployToOpenShift(Map params, String oc_user, String oc_password)
{
	println("Deply Params" + params)

	String environment = params.environment
	if (!(environment.trim())) {
		environment = 'dev'
		println("Defaulted environment to: " + environment)
	}
	List datacenters = params.datacenters
	if (datacenters?.size() == 0) {
		datacenters =['ny','den']
		println("Defaulted datacenters to: " + datacenters)
	}
}
