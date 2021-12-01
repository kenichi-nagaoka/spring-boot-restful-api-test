pipeline {
  agent any
  stages {
    stage ('build') {
      steps {
        sh 'mvn clean package'
      } 
    }
    stage('SCM') {
      checkout scm
    }
    stage('SonarQube Analysis') {
      def mvn = tool 'Default Maven';
      withSonarQubeEnv() {
        sh "${mvn}/bin/mvn clean verify sonar:sonar"
      }
    }
  }
}