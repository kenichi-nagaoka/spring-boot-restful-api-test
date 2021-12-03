pipeline {
  agent any
  stages {
    stage('SCM') {
      steps {
        checkout scm
      }
    }
    stage('SonarQube Analysis') {
      steps {
        script {
          def mvn = tool 'My Maven'
          withSonarQubeEnv() {
            sh "${mvn}/bin/mvn clean verify sonar:sonar"
          }
        }
      }
    }
    stage ('build') {
      steps {
        sh 'mvn clean package'
      } 
    }
  }
}