pipeline {
    agent any

    stages {
        stage('Checkout Codigo Fuente') {

            steps {
                script {
                    try {

                        checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github', url: 'git@github.com:amurcia/ucdevops-proyecto-.git']]])

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

