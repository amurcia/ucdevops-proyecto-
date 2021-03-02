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

        stage('Build') {
            steps {
                script {
                    echo "build"
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

                sleep(30)
                timeout(time: 1, unit: 'MINUTES') {
                    // Just in case something goes wrong, pipeline will be killed after a timeout
                    waitForQualityGate abortPipeline: true
                }

                script {

                    branchName = ""
                    if (!env.BRANCH_NAME.contains("main")) {
                        branchName = "-Dsonar.branch.name=${env.BRANCH_NAME}"
                    }
                }

            }
        }

        stage('Build Docker Image') {
            agent any
            steps {
                script {

                    if (env.BRANCH_NAME.equals("main")) {

                        version = ":$BUILD_NUMBER"

                    } else {
                        version = ":" + env.BRANCH_NAME.replace("/", "-") + "-$BUILD_NUMBER"
                    }

                    dockerImageName = registry + version
                    dockerImage = docker.build "${dockerImageName}"
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }

                    if (env.BRANCH_NAME.equals("master")) {
                        docker.withRegistry('', registryCredential) {
                            dockerImage.push('latest')
                        }
                        sh "docker rmi " + $ { registry } + "latest"
                    }
                    sh "docker rmi $registry$version"
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


