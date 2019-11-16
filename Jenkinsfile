pipeline {
  agent any
  stages {
    stage('Compile') {
      steps {
        dir(path: 'src/main/java/interpreter/parser/') {
          sh ' java -classpath "../../../../../javacc-6.0/bin/lib/javacc.jar" javacc Grammar.jj'
        }

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