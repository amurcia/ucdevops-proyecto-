node {
    
    stage('Clone repo') {
        git branch: "develop", url: "https://github.com/amurcia/ucdevops-proyecto-.git", credentialsId: "github"
    }
    
    stage('SonarTests') {
        docker.image('newtmitch/sonar-scanner').inside('-v /var/run/docker.sock:/var/run/docker.sock') {
            sh "--version"
        }
    }
}
