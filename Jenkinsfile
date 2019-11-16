pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        sh 'ls -la'
        dir(path: 'src/main/java/interpreter/parser/')
        sh 'ls -la'
        sh ' java -classpath "../../../../../javacc-6.0/bin/lib/javacc.jar" javacc Grammar.jj'
        sh 'ls -la'
        dir(path: '$WORKSPACE')
        sh 'ls -la'
        sh 'mvn clean compile'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

  }
  post {
    always {
      junit 'target/surefire-reports/*.xml'
    }

  }
}