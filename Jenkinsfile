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
        sh "mvn test"
      }
    }
    stage('Create Container Image & Deploy') {
      steps {
        sh "curl -X POST -d '{}' 'https://cloudbuild.googleapis.com/v1/projects/phrasal-vent-332015/triggers/Webhook-for-jenkins:webhook?key=AIzaSyDsZelCWgIQO0GIUzpV-_XU97TMrcekqy0&secret=MlU6lT05FcrM_e6T_y6ohtNXTmR7D9KW'"
      }
    }
  }
  post {
    // Allureレポートを入れてみたがうまく動かないので後で対応
    always {
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
    success {
      slackSend color: "good", message: "Build Successful $JOB_NAME. See $BUILD_URL"
    }
    failure {
      slackSend color: "danger", message: "Build Failure $JOB_NAME. See $BUILD_URL"
    }
  }
}