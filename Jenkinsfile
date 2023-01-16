@Library("jenkins-pipeline@feature/CHE-98")

def pipe = new Pipeline('office-service')

pipeline {
    agent any
    stages {
        stage('Register parameters') {
            steps {
                script {
                    pipe.setStackName(env.DEV_STACK ?: env.BRANCH)
                    pipe.setCommit(env.GIT_COMMIT)
                    pipe.setBranch(env.BRANCH)
                    pipe.setBuildUrl(env.BUILD_URL)
                    pipe.setPathDockerfile("./Dockerfile")
                    pipe.registerBuildArg("ENV", "hmg")
                }
            }
        }
        stage('Build docker image and push') {
            steps {
                script {
                    pipe.buildImage(docker)
                }
            }
        }
        stage('Create stack on ECS') {
            steps {
                script {
                    pipe.createStack("",8080)
                }
            }
        }
        stage('Register Consul data') {
            steps {
                script {
                    def stackName = pipe.getStackName()
                    Utils.registerConsulData("devstack/${stackName}/API_DEVSTACK_office_BASE_URI", pipe.getApplicationEndpoint())
                }
            }
        }
    }
    post {
        always {
            deleteDir()
        }
        failure {
            script {
                pipe.notifyFailure((env.NOTIFICATION ?: '').tokenize(','))
            }
        }
        success {
            script {
                pipe.notifySuccess((env.NOTIFICATION ?: '').tokenize(','))
            }
        }
    }
}