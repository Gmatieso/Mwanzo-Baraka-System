pipeline {
    agent any

    tools {
        maven 'Maven-3.8.4' // Must match what you configured
        jdk 'Java-21.0.7'       // Must match what you configured
    }

    environment {
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
                sh 'docker build -t mwanzo-backend:latest .'
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                writeFile file: '.env', text: """
                    DATABASE_USER=${env.DATABASE_USER}
                    DATABASE_PASSWORD=${env.DATABASE_PASSWORD}
                    DATABASE_URL=${env.DATABASE_URL}
                """
                sh 'docker-compose down --remove-orphans'
                sh 'docker-compose up -d --build'
            }
        }

        stage('Health Check') {
            steps {
                sh 'curl -f http://localhost:8081/actuator/health || exit 1'
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
            echo 'Cleanup complete.'
        }
    }
}

