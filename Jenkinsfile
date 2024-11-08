pipeline {
    agent any
    tools {
        jdk "jdk17"
        maven "maven"
    }
    environment {
        registry = "ayouta/tp2_devops"
        registryCredential = 'docker_hub'
        dockerImage = ''
    }
    stages {
        stage("Compile") {
            steps {
                script {
                    echo "Compiling..."
                    sh "mvn clean compile -DskipTests"
                }
            }
        }

        stage("Build") {
            steps {
                script {
                    echo "Building the JAR file..."
                    sh "mvn package -DskipTests"
                }
            }
        }

        stage("Test") {
            steps {
                script {
                    echo "Test ..."
                    sh "mvn test -X"
                }
            }
        }
    }

}
   