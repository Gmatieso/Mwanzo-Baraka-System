pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6' // Must match the Maven tool name you defined in Jenkins
        jdk 'Java-17'       // Must match the JDK tool name you defined in Jenkins
    }

    environment {
        // Load environment variables from Jenkins credentials or directly define here (not recommended)
        DATABASE_USER = credentials('mwanzo-db-user')
        DATABASE_PASSWORD = credentials('mwanzo-db-password')
        DATABASE_URL = 'jdbc:postgresql://localhost:5432/mwanzo'
        DOCKER_BUILDKIT = 1
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Gmatieso/Mwanzo-Baraka-System', branch: 'main'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker build -t mwanzo-backend:latest .'
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    // Inject .env file or export environment variables inline
                    writeFile file: '.env', text: """
                        DATABASE_USER=${env.DATABASE_USER}
                        DATABASE_PASSWORD=${env.DATABASE_PASSWORD}
                        DATABASE_URL=${env.DATABASE_URL}
                    """

                    sh 'docker-compose down --remove-orphans'
                    sh 'docker-compose up -d --build'
                }
            }
        }

        stage('Health Check') {
            steps {
                script {
                    sh 'curl -f http://localhost:8081/actuator/health || exit 1'
                }
            }
        }
    }

    post {
        success {
            echo '✅ Deployment Successful'
        }
        failure {
            echo '❌ Deployment Failed'
        }
        cleanup {
            // Optional: Clean up docker images or containers here if needed
        }
    }
}
