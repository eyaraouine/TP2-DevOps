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

        stage("Code Quality check") {
            steps {
                script {
                    echo "Running SonarQube Scanner..."
                    withSonarQubeEnv() {
                        sh "mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=TP2_DevOps \
                        -Dsonar.projectName='TP2_DevOps' \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.token=sqp_40be48237bfb7cb8f6fe4084f0b5fa40353a3e56"
                    }
                }
            }
        }
    }
    post {
        always {
            junit "target/surefire-reports/*.xml"
        }
    }
}
