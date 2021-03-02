pipeline {
    agent any

    stages {
        stage('Checkout'){

            steps{
                script{

                    checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'git@github.com:amurcia/ucdevops-proyecto-.git']]])


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

    }
}

    }
}

