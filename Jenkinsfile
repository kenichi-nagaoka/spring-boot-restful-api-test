pipeline {
  agent any
  stages {
    stage ('build') {
      steps {
        sh 'mvn clean package'
      } 
    }
    stage('SCM') {
      steps {
        checkout scm
      }
    }
    stage('SonarQube Analysis') {
      steps {
        echo 'Hello Sonar'
        script {
          def mvn = tool 'Default Maven'
          withSonarQubeEnv() {
            sh "${mvn}/bin/mvn clean verify sonar:sonar"
          }
        }
      }
    }
  }
}