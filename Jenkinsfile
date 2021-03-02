node {
    
    stage('Clone repo') {
       checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'GitHub', url: 'https://github.com/amurcia/ucdevops-proyecto-.git']]])
    }
    
    stage('SonarTests') {
        docker.image('newtmitch/sonar-scanner').inside('-v /var/run/docker.sock:/var/run/docker.sock') {
            sh "--version"
        }
    }
}
