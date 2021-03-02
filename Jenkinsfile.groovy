pipeline {
    agent any

    environment {
        registry = "amurciac/proyecto-api"
        registryCredential = 'dockerhub'
        dockerImage = ''
        branchName = ''
        version = ''
    }
    options {
        timestamps()
        skipDefaultCheckout()      // Don't checkout automatically
    }

    stages {
        stage('Checkout Codigo Fuente') {

            steps {
                script {
                    try {

                        checkout([$class: 'GitSCM', branches: [[name: "${env.BRANCH_NAME}"]], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'git@github.com:amurcia/ucdevops-proyecto-.git']]])

                    } catch (all) {
                        currentBuild.result = 'FAILURE'
                    }

                }
            }

        }

        stage('Sonarqube') {
            environment {
                scannerHome = tool 'SonarQubeScanner'
            }
            steps {
                withSonarQubeEnv('sonarqube') {

                    sh '''${scannerHome}/bin/sonar-scanner \
                -Dsonar.projectKey=ucdevops-proyecto \
                -Dsonar.sources=. \
                -Dsonar.host.url=http://sandbox.priv:9090 \
                -Dsonar.login=admin \
                -Dsonar.password=1q2w3e4r'''
                }

                script {

                    branchName = ""
                    if (!env.BRANCH_NAME.contains("main")) {
                        branchName = "-Dsonar.branch.name=${env.BRANCH_NAME}"
                    }
                }

            }
        }

    }
    post {
        always {
            echo 'Fin del Procesdo de Pipeline'
            deleteDir()
        }
    }
}

