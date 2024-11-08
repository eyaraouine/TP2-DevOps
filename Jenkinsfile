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
                    sh "java --version"
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

        stage("Code Quality check") {
            steps {
                script {
                    echo "Running SonarQube Scanner..."
                    withSonarQubeEnv() {
                        sh """
                        mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=TP2_DevOps \
                        -Dsonar.projectName='TP2_DevOps' \
                        -Dsonar.host.url=http://localhost:9000 \
                        -Dsonar.token="sqp_40be48237bfb7cb8f6fe4084f0b5fa40353a3e56"
                        """
                    }
                }
            }
        }

        stage('Snyk Security Test') {
            steps {
                echo 'Testing...'
                script {
                    // Give execute permissions to the mvnw file
                    sh 'chmod +x ./mvnw'
                    // Run the dependency tree command to verify maven wrapper works
                    sh './mvnw dependency:tree -DoutputType=dot --batch-mode --non-recursive --file="pom.xml"'
                }
                snykSecurity(
                    snykInstallation: 'snyk',
                    snykTokenId: 'snyk_cred2',
                    failOnIssues: false,
                    failOnError: false
                )
            }
        }

        stage('Building image') {
            steps {
                script {
                    dockerImage = docker.build("${registry}:${BUILD_NUMBER}")
                }
            }
        }

        stage('Upload Image') {
            steps {
                script {
                    docker.withRegistry('', registryCredential) {
                        dockerImage.push()
                    }
                }
            }
        }
    }

    
}
