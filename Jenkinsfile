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
    stage('Build & Deploy') {
      steps {
        script {
          sh "curl https://cloudbuild.googleapis.com/v1/projects/phrasal-vent-332015/triggers/Webhook-for-jenkins:webhook?key=AIzaSyDsZelCWgIQO0GIUzpV-_XU97TMrcekqy0&secret=MlU6lT05FcrM_e6T_y6ohtNXTmR7D9KW"
        }
      }
    }
    //stage ('Build') {
      //steps {
        //sh 'mvn clean package'
      //}
    // gcloudコマンドでデプロイ、Cloud BuildのWebhookイベント用のURLへPOSTする形にする。Cloud Build側で新しいトリガーを作成しておく
    }
  }
}