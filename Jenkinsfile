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
    stage ('Build') {
      steps {
        sh 'mvn clean package'
      }
    // gcloudコマンドでデプロイ、Cloud BuildのWebhookイベント用のURLへPOSTする形にする。Cloud Build側で新しいトリガーを作成しておく
    }
  }
}