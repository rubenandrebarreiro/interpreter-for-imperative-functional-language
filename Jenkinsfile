pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        sh 'mvn clean copile'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

  }
}