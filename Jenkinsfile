pipeline {
  agent any
  stages {
    stage('SonarQube Analysis') {
      steps {
        script {
          def mvn = tool 'My Maven'
          withSonarQubeEnv() {
            sh "${mvn}/bin/mvn sonar:sonar"
          }
        }
      }
    }
    stage('Build & Test') {
      steps {
        sh "mvn package"
      }
      POST {
        ALWAYS {
          script {
            allure([
              includeProperties: false,
              jdk: '',
              properties: [],
              reportBuildPolicy: 'ALWAYS',
              results: [[path: 'target/allure-results']]
            ])
          }
        }
      }
    }
   // stage('Reports') {
     // steps {
       // script {
         // allure([
           // includeProperties: false,
          //  jdk: '',
           // properties: [],
           // reportBuildPolicy: 'ALWAYS',
           // results: [[path: 'target/allure-results']]
         // ])
       // }
     // }
    //}
    stage('Create Container Image & Deploy') {
      steps {
        sh "curl -X POST -d '{}' 'https://cloudbuild.googleapis.com/v1/projects/phrasal-vent-332015/triggers/Webhook-for-jenkins:webhook?key=AIzaSyDsZelCWgIQO0GIUzpV-_XU97TMrcekqy0&secret=MlU6lT05FcrM_e6T_y6ohtNXTmR7D9KW'"
      }
    }
  }
}