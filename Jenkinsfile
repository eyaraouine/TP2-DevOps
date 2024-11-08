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
               steps{
                script{
                    echo "Running SonarQube Scanner..."
                    withSonarQubeEnv() {
                        sh "mvn verify sonar:sonar -Dsonar.url=http://172.31.240.1:9000/ -Dsonar.login=sqp_5d1cefc78fe33d32ac8610f5bedf8859ff91f4e2 -Dsonar.projectKey=TP2_DevOps -Dsonar.projectName=TP2_DevOps"
                    }
                }
            }
            
        }}

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
